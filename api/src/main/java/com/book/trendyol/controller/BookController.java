package com.book.trendyol.controller;

import com.book.trendyol.entity.Book;
import com.book.trendyol.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${server.base.url}")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllBooks() {
        List<Book> response = bookRepository.getAllBooks();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBook(@RequestBody @Valid Book book) {
        Book response = bookRepository.addBook(book);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{bookId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBook(@PathVariable int bookId) {
        Book response = bookRepository.getBookById(bookId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/clear",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> clearBooks() {
        bookRepository.clearBooks();
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
