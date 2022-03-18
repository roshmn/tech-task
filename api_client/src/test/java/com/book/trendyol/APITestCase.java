package com.book.trendyol;

import com.book.trendyol.service.BookService;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.assertj.core.api.Assertions.assertThat;

public class APITestCase {
    protected final BookService service = new BookService();
    protected final SoftAssertions softy = new SoftAssertions();

    @BeforeMethod(description = "Clean storage before each test", alwaysRun = true)
    public void doBeforeEach() {
        service.clearStorage();
        var books = service.getBooks().getBodyAsListObj();
        assertThat(books).isEmpty();
    }
}
