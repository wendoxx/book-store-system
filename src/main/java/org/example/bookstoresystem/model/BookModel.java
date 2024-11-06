package org.example.bookstoresystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "tb_books")
@Data
@EqualsAndHashCode(of = "id")
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "price")
    private double price;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "author",
            joinColumns = @JoinColumn(name = "bookId"),
            inverseJoinColumns = @JoinColumn(name = "authorId")
    )
    private Set<AuthorModel> author;
}
