package mx.dev.blank.dao;

import mx.dev.blank.entity.Book;
import mx.dev.blank.web.request.BookRequest;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

@Validated
public interface BookDAO {

    List<Book> getBooksByYearOfPublication(String order, @Min(1) Integer limit, @Min(1) Integer offset); //ASC-DESC

    List<String> getBookByAuthor(String author);

    List<String> getBooksByPriceMinMax(float priceMin, float priceMax);

    List<String> getBooksByDateStartEnd(Date startDate, Date endDate);

    List<String> getBooksByCategory(String category);

    Long getCountBooksByCategory(String category);

    List<Book> getBooksByCountAuthors(final long numAuthors);

    List<Book> getBooksOrderedByPages(String order, @Min(1) Integer limit, @Min(0) Integer offset);

    Book createBook(BookRequest bookRequest);

    Integer putBook(final int bookID, final BookRequest bookRequest);

    Integer deleteBook(final int bookID, final BookRequest bookRequest);

    Double getRankingByBook(final long book_id);
}
