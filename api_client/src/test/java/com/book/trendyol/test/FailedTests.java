package com.book.trendyol.test;

import com.book.trendyol.APITestCase;
import com.book.trendyol.entity.dao.Book;
import org.testng.annotations.Test;

import static com.book.trendyol.config.constants.TestThings.*;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

@Test(description = "Some tests that must be failed")
public class FailedTests extends APITestCase {

    @Test(description = "Check failed test")
    public void checkFailedTest() {
        Book book_1 = new Book(TEST_AUTHOR_1, TEST_TITLE_1);
        Book book_2 = new Book(TEST_AUTHOR_2, TEST_TITLE_2);

        service.createBook(book_1);
        service.createBook(book_2);

        var resp = service.getBooks();
        softy.assertThat(resp.getCode()).isEqualTo(HTTP_OK);
        softy.assertThat(resp.getBodyAsListObj()).isEmpty();

        softy.assertAll();
    }

    @Test(description = "Check one more failed test")
    public void checkFindBookByIdFailed() {
        Book book = new Book(TEST_AUTHOR_1, TEST_TITLE_1);

        var resp = service.createBook(book);
        softy.assertThat(resp.getCode()).isEqualTo(HTTP_CREATED);

        resp = service.getBook(2);
        softy.assertThat(resp.getCode()).isEqualTo(HTTP_OK);

        softy.assertAll();
    }
}
