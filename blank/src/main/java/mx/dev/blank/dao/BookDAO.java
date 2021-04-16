package mx.dev.blank.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Min;
import mx.dev.blank.entity.Book;
import mx.dev.blank.web.request.BookRequest;
import org.springframework.validation.annotation.Validated;

@Validated
public interface BookDAO {
  List<Book> getBooksByYearOfPublication(
      String order, @Min(1) Integer limit, @Min(0) Integer offset);

  List<String> getBookByAuthor(String author);

  List<String> getBooksByPrice(BigDecimal priceMin, BigDecimal priceMax);

  List<String> getBooksByDate(Date startDate, Date endDate);

  List<String> getBooksByCategory(String category);

  Long getAmountOfBooksByCategory(String category);

  List<Book> getBooksByPages(String order, @Min(1) Integer limit, @Min(0) Integer offset);

  List<Book> getBooksByAmountAuthors(long amountAuthors);

  Book createBook(BookRequest bookRequest);

  Integer updateBook(final Integer bookId, final BookRequest bookRequest);

  void deleteBook(final int bookId);

  Double getRankingByBook(final long book_id);
}
