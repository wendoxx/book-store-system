package org.example.bookstoresystem.dto.request;

import lombok.Data;

@Data
public class BookRequestDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
}