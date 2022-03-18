package com.book.trendyol.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookNotFoundException extends RuntimeException {
    private int bookId;
}
