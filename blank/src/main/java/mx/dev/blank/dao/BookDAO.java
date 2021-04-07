package mx.dev.blank.dao;

import mx.dev.blank.entity.Book;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface BookDAO {
    List<Book> getAll();
}
