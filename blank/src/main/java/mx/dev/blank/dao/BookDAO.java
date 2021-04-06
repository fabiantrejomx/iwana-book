package mx.dev.blank.dao;

import mx.dev.blank.entity.Book;
import mx.dev.blank.web.controller.request.BookFilterRequest;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface BookDAO {

    void add(@NotNull Book book);

    void update(@NotNull Book book);

    void delete(@NotNull Book book);

    Book findById(final long courseId);

    List<Book> getBooksByPublicationDateAsc(final BookFilterRequest request);

    List<Book> getBooksByPublicationDateDesc(final BookFilterRequest request);

    List<Book> getBooksByMinAndMaxPrice(final BookFilterRequest request);

    List<Book> getBooksByInitAndFinalPublicationDate(final BookFilterRequest request);

}
