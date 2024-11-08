package org.example.bookstoresystem.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.bookstoresystem.dto.request.BookRequestDTO;
import org.example.bookstoresystem.dto.response.BookResponseDTO;
import org.example.bookstoresystem.model.BookModel;
import org.example.bookstoresystem.repository.AuthorRepository;
import org.example.bookstoresystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    private static final Logger LOGGER = LogManager.getLogger();

    public BookResponseDTO getBookById(Long id){
        LOGGER.info("Getting book...");
        return bookRepository.findById(id)
                .map(BookResponseDTO::new)
                .orElseThrow(() -> {
                    LOGGER.error("Book not found.");
                    return new RuntimeException("Book not found");
                });
    }

    @Transactional
    public BookModel saveAndUpdateBook(BookRequestDTO bookRequestDTO) {
        BookModel book;

        if(bookRequestDTO.getId() != null && bookRepository.existsById(bookRequestDTO.getId())){
            LOGGER.info("Updating book...");
           book = bookRepository.findById(bookRequestDTO.getId()).get();
        } else {
            LOGGER.info("Saving book...");
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

    public BookResponseDTO getBookByTitle(String title){
        LOGGER.info("Getting book...");
        return bookRepository.findByTitle(title)
                .map(BookResponseDTO::new).orElseThrow(() -> {
                    LOGGER.error("Book not found.");
                    return new RuntimeException("Book not found.");
                });
    }

    public List<BookResponseDTO> getAllBooks(){
        LOGGER.info("Getting all books...");
        return bookRepository.findAll().stream().map(BookResponseDTO::new).toList();
    }

    public void deleteBookById(Long id){
        LOGGER.info("Deleting book...");
        bookRepository.deleteById(id);
    }
}
