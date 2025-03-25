package com.learning.book_api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
    description = "BookDto model information"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {


    private Long id;

    @Schema(description = "Title of the book")
    @NotEmpty(message = "Title must not be empty")
    private String title;

    @Schema(description = "Author of the book")
    @NotEmpty(message = "Author must not be empty")
    private String author;

    @Schema(description = "Genre of the book")
    @NotEmpty(message = "Genre must not be empty")
    private String genre;

    @Schema(description = "Price of the book")
    @NotNull(message = "Price must not be null")
    private Double price;

    @Schema(description = "ISBN of the book")
    @NotEmpty(message = "ISBN must not be empty")
    private String ISBN;

}
