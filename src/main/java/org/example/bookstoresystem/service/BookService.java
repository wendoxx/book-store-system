package org.example.bookstoresystem.service;

import org.example.bookstoresystem.dto.request.BookRequestDTO;
import org.example.bookstoresystem.dto.response.BookResponseDTO;
import org.example.bookstoresystem.model.AuthorModel;
import org.example.bookstoresystem.model.BookModel;
import org.example.bookstoresystem.repository.AuthorRepository;
import org.example.bookstoresystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public BookResponseDTO getBookById(Long id){
        return bookRepository.findById(id)
                .map(BookResponseDTO::new)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public BookModel saveAndUpdateBook(BookRequestDTO bookRequestDTO) {
        BookModel book;

        if(bookRequestDTO.getId() != null && bookRepository.existsById(bookRequestDTO.getId())){
           book = bookRepository.findById(bookRequestDTO.getId()).get();
        } else {
            book = new BookModel();
        }


        book.setAuthor(authorRepository.findById(bookRequestDTO.getAuthor()).stream().collect(Collectors.toSet()));
        book.setTitle(bookRequestDTO.getTitle());
        book.setDescription(bookRequestDTO.getDescription());
        book.setPrice(bookRequestDTO.getPrice());
        book.setReleaseDate(bookRequestDTO.getReleaseDate());
        book.setIsbn(bookRequestDTO.getIsbn());

        return bookRepository.save(book);
    }
}
