package org.example.bookstoresystem.controller;

import org.example.bookstoresystem.dto.request.AuthorRequestDTO;
import org.example.bookstoresystem.dto.response.AuthorResponseDTO;
import org.example.bookstoresystem.model.AuthorModel;
import org.example.bookstoresystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorModel> saveAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        return ResponseEntity.ok().body(authorService.saveAuthor(authorRequestDTO));
    }

    @PutMapping
    public ResponseEntity<AuthorModel> updateAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        return ResponseEntity.ok().body(authorService.saveAuthor(authorRequestDTO));
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id){
        return ResponseEntity.ok().body(authorService.getAuthorById(id));
    }

    @GetMapping("/by-name")
    public ResponseEntity<AuthorResponseDTO> getAuthorByName(@RequestParam String name){
        return ResponseEntity.ok().body(authorService.getAuthorByName(name));
    }

    @GetMapping("/all-authors")
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors(){
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @DeleteMapping("/delete-author/{id}")
    public ResponseEntity<AuthorResponseDTO> deleteAuthorById(@PathVariable Long id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }

}