package mx.dev.blank.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.dao.BookJpaDAO;
import mx.dev.blank.entity.Book;
import mx.dev.blank.web.request.BookRequest;
import mx.dev.blank.web.response.BookWithRanking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
  @Autowired BookJpaDAO bookJpaDAO;

  public List<Book> getOrderedBooks(String order, Integer limit, Integer offset) {
    return bookJpaDAO.getBooksByYearOfPublication(order, limit, offset);
  }

  public List<Book> getOrderedBooksByPages(String order, Integer limit, Integer offset) {
    return bookJpaDAO.getBooksByPages(order, limit, offset);
  }

  public List<String> getBooksByAuthor(String author) {
    return bookJpaDAO.getBookByAuthor(author);
  }

  public List<String> getBooksByPrice(final BigDecimal priceMin, final BigDecimal priceMax) {
    return bookJpaDAO.getBooksByPrice(priceMin, priceMax);
  }

  public List<String> getBooksByDate(final Date startDate, final Date endDate) {
    return bookJpaDAO.getBooksByDate(startDate, endDate);
  }

  public List<String> getBooksByCategory(final String category) {

    return bookJpaDAO.getBooksByCategory(category);
  }

  public Long getAmountOfBooksByCategory(final String category) {
    return bookJpaDAO.getAmountOfBooksByCategory(category);
  }

  public List<Book> getBooksByAmountAuthors(final long authors) {
    return bookJpaDAO.getBooksByAmountAuthors(authors);
  }

  public List<BookWithRanking> getBooksWithScore(String order, Integer limit, Integer offset) {
    List<Book> books = bookJpaDAO.getBooksByPages(order, limit, offset);
    List<BookWithRanking> booksRankings = new ArrayList<>();

    for (Book book : books) {
      BookWithRanking br = new BookWithRanking();
      Double ranking = bookJpaDAO.getRankingByBook(book.getId());
      br.setScore(ranking);
      booksRankings.add(br);
    }
    return booksRankings;
  }

  /*CRUD*/

  @Transactional
  public Book createBook(final BookRequest bookRequest) {
    Book book = bookJpaDAO.createBook(bookRequest);
    return book;
  }

  @Transactional
  public Integer updateBook(final Integer bookId, final BookRequest bookRequest) {
    Integer isUpdated = bookJpaDAO.updateBook(bookId, bookRequest);
    return isUpdated;
  }

  @Transactional
  public void deleteBook(final int bookId) {
    bookJpaDAO.deleteBook(bookId);
  }
}
