package org.example.bookstoresystem.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.bookstoresystem.dto.request.AuthorRequestDTO;
import org.example.bookstoresystem.dto.response.AuthorResponseDTO;
import org.example.bookstoresystem.model.AuthorModel;
import org.example.bookstoresystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    private static final Logger LOGGER = LogManager.getLogger();

    @Transactional
    public AuthorModel saveAndUpdateAuthor(AuthorRequestDTO authorRequestDTO){
        AuthorModel author;

        if (authorRequestDTO.getId() != null && authorRepository.existsById(authorRequestDTO.getId())){
            LOGGER.info("Updating author...");
            author = authorRepository.findById(authorRequestDTO.getId()).get();
        }else {
            LOGGER.info("Saving author...");
            author = new AuthorModel();
        }

        author.setId(authorRequestDTO.getId());
        author.setName(authorRequestDTO.getName());

        return authorRepository.save(author);
    }

    public AuthorResponseDTO getAuthorById(Long id){
        LOGGER.info("Getting author...");
        return authorRepository.findById(id)
                .map(AuthorResponseDTO::new)
                .orElseThrow(() -> {
                    LOGGER.error("Author not found.");
                    return new RuntimeException("Author not found.");
                });
    }

    public AuthorResponseDTO getAuthorByName(String name){
        LOGGER.info("Getting author");
        return authorRepository.findByName(name)
                .map(AuthorResponseDTO::new)
                .orElseThrow(() -> {
                    LOGGER.error("Author not found.");
                    return new RuntimeException("Author not found");
                });
    }

    public List<AuthorResponseDTO> getAllAuthors(){
        LOGGER.info("Getting all authors...");
        return authorRepository.findAll().stream().map(AuthorResponseDTO::new).toList();
    }

    public void deleteAuthorById(Long id){
        LOGGER.info("Deleting author...");
        authorRepository.deleteById(id);
    }

}
