package com.book.trendyol.test;

import com.book.trendyol.APITestCase;
import com.book.trendyol.entity.dao.Book;
import org.testng.annotations.Test;

import static com.book.trendyol.config.constants.TestThings.*;
import static java.net.HttpURLConnection.*;

@Test
public class CreateBookTests extends APITestCase {

    @Test(description = "Check that field 'title is required'")
    public void checkAuthorIsRequiredTest() {
        Book book = new Book(null, TEST_TITLE_1);
        var resp = service.createBook(book);

        softy.assertThat(resp.getCode()).isEqualTo(HTTP_BAD_REQUEST);
        softy.assertThat(resp.getApiError().getMessage())
                .contains(AUTHOR)
                .contains(IS_REQUIRED);

        softy.assertAll();
    }

    @Test(description = "Check 'title' field is required")
    public void checkTitleIsRequiredTest() {
        Book book = new Book(TEST_AUTHOR_1, null);
        var resp = service.createBook(book);

        softy.assertThat(resp.getCode()).isEqualTo(HTTP_BAD_REQUEST);
        softy.assertThat(resp.getApiError().getMessage())
                .contains(TITLE)
                .contains(IS_REQUIRED);

        softy.assertAll();
    }

    @Test(description = "Check 'author' field can't be empty")
    public void checkAuthorNotEmpty() {
        Book book = new Book(EMPTY_STR, TEST_TITLE_1);
        var resp = service.createBook(book);

        softy.assertThat(resp.getCode()).isEqualTo(HTTP_BAD_REQUEST);
        softy.assertThat(resp.getApiError().getMessage())
                .contains(AUTHOR)
                .contains(NOT_EMPTY);

        softy.assertAll();
    }

    @Test(description = "Check 'title' field can't be empty")
    public void checkTitleNotEmpty() {
        Book book = new Book(TEST_AUTHOR_1, EMPTY_STR);
        var resp = service.createBook(book);

        softy.assertThat(resp.getCode()).isEqualTo(HTTP_BAD_REQUEST);
        softy.assertThat(resp.getApiError().getMessage())
                .contains(TITLE)
                .contains(NOT_EMPTY);

        softy.assertAll();
    }

    @Test(description = "Check 'id' field is read-only and can't be set")
    public void checkIdIsReadOnlyTest() {
        Book invalidBook = new Book(1, TEST_AUTHOR_2, TEST_TITLE_2);
        var resp = service.createBook(invalidBook);

        softy.assertThat(resp.getCode()).isEqualTo(HTTP_BAD_REQUEST);
        softy.assertThat(resp.getApiError().getMessage())
                .contains(ID)
                .contains(READ_ONLY);

        softy.assertAll();
    }

    @Test(description = "Check book can be created.")
    public void checkBookCreatedTest() {
        Book book = new Book(TEST_AUTHOR_2, TEST_TITLE_2);
        var resp = service.createBook(book);

        Book respBody = resp.getBodySingleObj();
        softy.assertThat(resp.getCode()).isEqualTo(HTTP_CREATED);
        softy.assertThat(respBody.getAuthor()).isEqualTo(book.getAuthor());
        softy.assertThat(respBody.getTitle()).isEqualTo(book.getTitle());

        resp = service.getBook(respBody.getId());
        respBody = resp.getBodySingleObj();
        softy.assertThat(resp.getCode()).isEqualTo(HTTP_OK);
        softy.assertThat(respBody.getAuthor()).isEqualTo(book.getAuthor());
        softy.assertThat(respBody.getTitle()).isEqualTo(book.getTitle());

        softy.assertAll();
    }

    @Test(description = "Check you can't create the same book twice")
    public void checkBookDuplicateTest() {
        Book book = new Book(TEST_AUTHOR_2, TEST_TITLE_2);

        var resp = service.createBook(book);
        softy.assertThat(resp.getCode()).isEqualTo(HTTP_CREATED);

        resp = service.createBook(book);
        softy.assertThat(resp.getCode()).isEqualTo(HTTP_BAD_REQUEST);

        softy.assertAll();
    }
}
