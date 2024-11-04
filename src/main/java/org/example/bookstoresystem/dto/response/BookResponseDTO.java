package org.example.bookstoresystem.dto.response;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.example.bookstoresystem.model.BookModel;

import java.time.LocalDate;

@Data
public class BookResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String isbn;
    private LocalDate releaseDate;
    private double price;

    public BookResponseDTO(BookModel bookModel){
        this.id = bookModel.getId();
        this.title = bookModel.getTitle();
        this.description = bookModel.getDescription();
        this.isbn = bookModel.getIsbn();
        this.releaseDate = bookModel.getReleaseDate();
        this.price = bookModel.getPrice();
    }
}