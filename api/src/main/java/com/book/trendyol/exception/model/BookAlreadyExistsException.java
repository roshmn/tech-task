package com.book.trendyol.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookAlreadyExistsException extends RuntimeException {
    private String author;
    private String title;
}
