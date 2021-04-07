package mx.dev.blank.web.controller;

import mx.dev.blank.entity.Author;
import mx.dev.blank.entity.Category;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import mx.dev.blank.entity.Book;
import mx.dev.blank.service.BookService;
import mx.dev.blank.web.controller.request.BookRequest;

public class BookSearchByFieldRestControllerMocMvcTest {

    private BookService bookService;

    private MockMvc mvc;

    SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-mm-dd");
    Date date = dateFor.parse("1994-05-02");

    public BookSearchByFieldRestControllerMocMvcTest() throws ParseException {
    }

    @BeforeMethod
    public void setUp() throws Exception {
        bookService = mock(BookService.class);

        final BookSearchByFieldRestController controller =
                new BookSearchByFieldRestController(
                        bookService);

        mvc =
                MockMvcBuilders.standaloneSetup(controller)
                        .setControllerAdvice(new ExceptionControllerAdvice())
                        .build();
    }

    @Test
    public void getBooksListByPublicationDateOrderByAsc() throws Exception {
        final Author testAuthor = new Author("Stephen", "King", "King", date);
        final Category testCategory = new Category("Historia");
        final Book testBook = new Book("La ilidada",
                300,
                testAuthor,
                "Mirabillo",
                15287456,
                testCategory,
                300,
                "si",
                date);
        testBook.setId(1);
        when(bookService.getBooksListByAnyFieldAsc(any(BookRequest.class), "publicationDate")
                                                        .addAll(Collections.singletonList(testBook)));

        mvc.perform(
                get(URI.create("/books/publication_date/asc"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.books").exists())
                .andExpect(jsonPath("$.books").value(hasSize(1)))
                .andExpect(jsonPath("$.books[*].publicationDate").value(contains(1)));
    }

}
