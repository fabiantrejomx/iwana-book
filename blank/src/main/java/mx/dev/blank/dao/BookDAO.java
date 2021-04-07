package mx.dev.blank.dao;

import mx.dev.blank.entity.Book;
import mx.dev.blank.web.controller.request.BookFilterRequest;
import mx.dev.blank.web.controller.request.BookRequest;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Validated
public interface BookDAO {

    void add(@NotNull Book book);

    void update(@NotNull Book book);

    void delete(final int bookId);

    Book findById(final long courseId);

    /*
     * 1.
     * 2.
     * 9.
     */

    List<Book> getBooksByAnyFieldOrderByAsc(final BookRequest request, final String field);

    List<Book> getBooksByAnyFieldOrderByDesc(final BookRequest request, final String field);

    //3.

    List<Book> getBooksByAuthorNameOrLastname(final String nameOrLastname);

    //4.

    List<Book> getBooksByMinAndMaxPrice(final int minPrice, final int maxPrice);

    //5.

    List<Book> getBooksByXAuthorsNumber(final int authorsNumber);

    //6.

    List<Book> getBooksByInitAndFinalPublicationDate(final Date initialDate, final Date finalDate);

    //7.

    int countBooksByXCategory(@Min(1) String category);

    //8.

    List<Book> getBooksByCategory(final String category);



}
