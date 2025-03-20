package com.learning.book_api.service;


import com.learning.book_api.exception.EntityAlreadyExistsException;
import com.learning.book_api.exception.EntityNotFoundException;
import com.learning.book_api.model.Book;
import com.learning.book_api.model.dto.BookDto;
import com.learning.book_api.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    @Override
    public BookDto addBook(BookDto book) {
        Book bookToSave = modelMapper.map(book, Book.class);
        if(bookAlreadyStored(bookToSave.getISBN())){
            throw new EntityAlreadyExistsException("Book with ISBN: " + book.getISBN() + " already stored");
        }
        return modelMapper.map(bookRepository.save(bookToSave), BookDto.class);
    }

    @Override
    public BookDto updateBook(BookDto book, Long id) {
        return bookRepository.findById(id).map(b -> {
            b.setTitle(book.getTitle());
            b.setAuthor(book.getAuthor());
            b.setGenre(book.getGenre());
            b.setPrice(book.getPrice());
            b.setISBN(book.getISBN());
            return modelMapper.map(bookRepository.save(b), BookDto.class);
        }).orElseThrow(() -> new EntityNotFoundException("Book not found to update"));
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookRepository.findById(id).map(b -> modelMapper.map(b, BookDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(b -> modelMapper.map(b, BookDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)){
            throw new EntityNotFoundException("Book not found to delete");
        }
        bookRepository.deleteById(id);
    }

    private boolean bookAlreadyStored(String isbn) {
        return bookRepository.findByISBN(isbn).isPresent();
    }

}