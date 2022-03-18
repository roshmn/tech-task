package com.book.trendyol.repository;

import com.book.trendyol.entity.Book;
import com.book.trendyol.exception.model.BookAlreadyExistsException;
import com.book.trendyol.exception.model.BookIdNotWritableException;
import com.book.trendyol.exception.model.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class BookRepository {
    private final List<Book> booksList;

    public BookRepository() {
        this.booksList = new ArrayList<>();
    }

    public List<Book> getAllBooks() {
        log.info("Books array: " + booksList);
        return booksList;
    }

    public Book addBook(Book book) {
        if (book.getId() != null) {
            log.error("Property 'id' is readonly and will be generated automatically!");
            throw new BookIdNotWritableException();
        }

        boolean isExist = booksList.stream()
                .map(b -> b.getAuthor() + b.getTitle())
                .anyMatch(b -> b.equals(book.getAuthor() + book.getTitle()));

        if (isExist) {
            log.error(String.format("Book with 'author' = '%s' and 'title' = '%s' already exists!",
                    book.getAuthor(), book.getTitle()));
            throw new BookAlreadyExistsException(book.getAuthor(), book.getTitle());
        }

        Book newOne = new Book(booksList.size() + 1, book.getAuthor(), book.getTitle());
        booksList.add(newOne);
        log.info("Added new book entity: " + book);
        return newOne;
    }

    public Book getBookById(int bookId) {
        Book founded = booksList.stream()
                .filter(b -> b.getId() == bookId)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(bookId));
        log.info("Founded book: " + founded);
        return founded;
    }

    public void clearBooks() {
        booksList.clear();
        log.info("Books array cleared successfully!");
    }
}
