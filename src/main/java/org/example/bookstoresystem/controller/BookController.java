package org.example.bookstoresystem.controller;

import org.example.bookstoresystem.dto.request.BookRequestDTO;
import org.example.bookstoresystem.dto.response.BookResponseDTO;
import org.example.bookstoresystem.model.BookModel;
import org.example.bookstoresystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookModel> saveBook(@RequestBody BookRequestDTO book){
        return ResponseEntity.status(201).body(bookService.saveAndUpdateBook(book));
    }

    @PutMapping
    public ResponseEntity<BookModel> updateBook(@RequestBody BookRequestDTO bookRequestDTO){
        return ResponseEntity.status(201).body(bookService.saveAndUpdateBook(bookRequestDTO));
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<BookResponseDTO> findBookById(@PathVariable Long id){
        return ResponseEntity.ok().body(bookService.getBookById(id));
    }

    @GetMapping("/by-title")
    public ResponseEntity<BookResponseDTO> findBookByName(@RequestBody String title){
        return ResponseEntity.ok().body(bookService.getBookByTitle(title));
    }

    @DeleteMapping("/delete-book")
    public ResponseEntity<BookResponseDTO> deleteBookById(@RequestBody Long id){
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }
}