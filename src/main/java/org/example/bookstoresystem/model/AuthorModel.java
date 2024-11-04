package org.example.bookstoresystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
@Table(name = "tb_author")
@EqualsAndHashCode(of = "id")
public class AuthorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "author")
    private Set<BookModel> books;
}