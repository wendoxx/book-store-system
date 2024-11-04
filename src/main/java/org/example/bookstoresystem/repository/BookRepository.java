package org.example.bookstoresystem.repository;

import org.example.bookstoresystem.model.AuthorModel;
import org.example.bookstoresystem.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
    Optional<AuthorModel> findAllByAuthor(String author);
}
