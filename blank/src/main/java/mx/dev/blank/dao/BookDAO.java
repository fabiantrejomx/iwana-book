package mx.dev.blank.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import mx.dev.blank.entity.Book;
import org.springframework.validation.annotation.Validated;

@Validated
public interface BookDAO {

  void create(@NotNull Book book);

  void update(@NotNull Book book);

  void delete(@NotNull Book book);

  Book findById(@Min(1) int id);

  List<Book> getBooksByYearOfPublication(
      String order, @Min(1) Integer limit, @Min(0) Integer offset);

  List<String> getBookByAuthor(String author);

  List<String> getBooksByPrice(BigDecimal priceMin, BigDecimal priceMax);

  List<String> getBooksByDate(Date startDate, Date endDate);

  List<String> getBooksByCategory(String category);

  Long getAmountOfBooksByCategory(String category);

  List<Book> getBooksByPages(String order, @Min(1) Integer limit, @Min(0) Integer offset);

  List<Book> getBooksByAmountAuthors(long amountAuthors);

  Double getRankingByBook(final long book_id);
}
