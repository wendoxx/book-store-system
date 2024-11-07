package org.example.bookstoresystem.dto.response;

import lombok.Data;
import org.example.bookstoresystem.model.AuthorModel;
import org.example.bookstoresystem.model.BookModel;

import java.util.List;
import java.util.Set;

@Data
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private Set<BookModel> books;

    public AuthorResponseDTO(AuthorModel authorModel){
        this.id = authorModel.getId();
        this.name = authorModel.getName();
        this.books = authorModel.getBooks();
    }
}