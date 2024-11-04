package org.example.bookstoresystem.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookModel {
    private Long id;
    private String title;
    private String description;
    private String isbn;
    private LocalDate releaseDate;
    private double price;
}