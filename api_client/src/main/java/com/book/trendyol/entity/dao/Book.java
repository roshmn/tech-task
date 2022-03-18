package com.book.trendyol.entity.dao;

import java.util.Objects;

public class Book {
    private Integer id;
    private String author;
    private String title;

    public Book() {
    }

    public Book(Integer id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!Objects.equals(id, book.id)) return false;
        if (!Objects.equals(author, book.author)) return false;
        return Objects.equals(title, book.title);
    }
}
