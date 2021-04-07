package mx.dev.blank.dao;

import mx.dev.blank.entity.Book;
import mx.dev.blank.web.form.BookForm;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
public interface BookDAO {
    List<Book> getAll();
    List<Book> getAllOrderAsc(final String order, final @Min(1)Integer limit, final @Max(0) Integer offset);
    List<Book> getAllOrderDesc(final String order, final @Min(1)Integer limit, final @Max(0) Integer offset);
    Long getBookByCategory(final String category);
    List<String> getBookByAutor(final String autor);

    Book createBook(BookForm form);
}
