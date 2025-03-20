package com.learning.book_api.service;




import com.learning.book_api.model.Book;
import com.learning.book_api.model.dto.BookDto;

import java.util.List;

public interface IBookService {

    public BookDto addBook(BookDto book);

    public BookDto updateBook(BookDto book, Long id);

    public BookDto getBookById(Long id);

    public List<BookDto> getAllBooks();

    public void deleteBook(Long id);

}
