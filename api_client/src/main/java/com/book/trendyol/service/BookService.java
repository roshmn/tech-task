package com.book.trendyol.service;

import com.book.trendyol.core.RestClient;
import com.book.trendyol.entity.ResponseObj;
import com.book.trendyol.entity.dao.ApiError;
import com.book.trendyol.entity.dao.Book;
import com.book.trendyol.utils.URLUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.book.trendyol.config.constants.Endpoints.*;

public class BookService {
    private final RestClient client;

    private final Gson gson;
    private final URLUtils utils;

    public BookService() {
        this.client = new RestClient();
        this.gson = new Gson();
        this.utils = new URLUtils();
    }

    public ResponseObj<Book> getBook(int id) {
        HttpResponse<String> response = client.get(utils.getUrl() + SINGLE_BOOK.formatted(id));
        return parseResponse(response, Book.class);
    }

    public ResponseObj<Book> getBooks() {
        HttpResponse<String> response = client.get(utils.getUrl() + BOOKS);
        return parseResponse(response, Book.class);
    }

    public ResponseObj<Book> createBook(Book book) {
        String bodyStr = gson.toJson(book);
        HttpResponse<String> response = client.put(utils.getUrl() + BOOKS, bodyStr);
        return parseResponse(response, Book.class);
    }

    public ResponseObj<Void> clearStorage() {
        HttpResponse<String> response = client.delete(utils.getUrl() + CLEAR_BOOKS);
        return parseResponse(response, Void.class);
    }

    private <T> ResponseObj<T> parseResponse(HttpResponse<String> response, Class<T> clazz) {
        int statusCode = response.statusCode();
        JsonElement parsed = JsonParser.parseString(response.body());

        // return as error entity
        if (statusCode >= 400) {
            return new ResponseObj<>(statusCode, gson.fromJson(parsed, ApiError.class));
        }

        // return as list of dao entities
        if (parsed.isJsonArray()) {
            List<T> outList = new ArrayList<>();
            parsed.getAsJsonArray().forEach(e -> outList.add(gson.fromJson(e, clazz)));
            return new ResponseObj<>(statusCode, outList);
        }

        // else return single dao
        return new ResponseObj<>(statusCode, gson.fromJson(parsed, clazz));
    }
}
