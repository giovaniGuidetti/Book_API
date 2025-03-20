package com.learning.book_api.controller;


import com.learning.book_api.model.Book;
import com.learning.book_api.model.dto.BookDto;
import com.learning.book_api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping("/add")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto book) {
        BookDto createdBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto book, @PathVariable Long id) {
        BookDto updatedBook = bookService.updateBook(book, id);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

}