package org.example.bookstoresystem.dto.response;

import lombok.Data;
import org.example.bookstoresystem.model.BookModel;

import java.util.List;

@Data
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private List<BookModel> books;
}