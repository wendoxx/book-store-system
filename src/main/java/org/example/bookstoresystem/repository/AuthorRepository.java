package org.example.bookstoresystem.repository;

import org.example.bookstoresystem.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {
    Optional<AuthorModel> findByName(String name);
}
