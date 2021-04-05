package mx.dev.blank.dao;

import mx.dev.blank.entity.Book;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface BookDAO {

    void create(@NotNull Book book);

    void update(@NotNull Book book);

    void delete(@NotNull Book book);

}
