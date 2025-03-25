package com.learning.book_api.controller;


import com.learning.book_api.model.Book;
import com.learning.book_api.model.dto.BookDto;
import com.learning.book_api.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for books",
        description = "CRUD REST APIs - Create / Read / Update / Delete books"
)
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;

    @Operation(
            summary = "Get all books",
            description = "Get all books from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Found all books"
    )
    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @Operation(
            summary = "Add books",
            description = "Add books to the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created book"
    )
    @PostMapping("/add")
    public ResponseEntity<BookDto> addBook(@RequestBody @Valid BookDto book) {
        BookDto createdBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @Operation(
            summary = "Update books",
            description = "Update books in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Updated book"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody @Valid BookDto book, @PathVariable Long id) {
        BookDto updatedBook = bookService.updateBook(book, id);
        return ResponseEntity.ok(updatedBook);
    }

    @Operation(
            summary = "Delete books",
            description = "Delete books from the database"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Deleted book"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get book by id",
            description = "Get book by id from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Found book by id"
    )
    @GetMapping("/book/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

}