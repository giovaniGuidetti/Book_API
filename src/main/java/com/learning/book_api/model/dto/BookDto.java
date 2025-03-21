package com.learning.book_api.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;
    @NotEmpty(message = "Title must not be empty")
    private String title;
    @NotEmpty(message = "Author must not be empty")
    private String author;
    @NotEmpty(message = "Genre must not be empty")
    private String genre;
    @NotNull(message = "Price must not be null")
    private Double price;
    @NotEmpty(message = "ISBN must not be empty")
    private String ISBN;

}
