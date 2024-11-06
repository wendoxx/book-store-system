package org.example.bookstoresystem.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequestDTO {
    private Long id;
    private String title;
    private Long author;
    private String isbn;
    private String description;
    private double price;
    private LocalDate releaseDate;
}