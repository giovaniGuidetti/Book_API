package com.learning.book_api.controller;


import com.learning.book_api.model.Book;
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
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @RequestMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book createdBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @RequestMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable Long id) {
        Book updatedBook = bookService.updateBook(book, id);
        return ResponseEntity.ok(updatedBook);
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

}
