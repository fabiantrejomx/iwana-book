package mx.dev.blank.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.dao.AuthorDAO;
import mx.dev.blank.dao.BookDAO;
import mx.dev.blank.dao.CategoryDAO;
import mx.dev.blank.entity.Author;
import mx.dev.blank.entity.Book;
import mx.dev.blank.entity.Category;
import mx.dev.blank.exception.ResourceNotFoundException;
import mx.dev.blank.web.request.BookRequest;
import mx.dev.blank.web.response.BookWithRanking;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookDAO bookDAO;
  private final AuthorDAO authorDAO;
  private final CategoryDAO categoryDAO;

  /*CRUD*/

  @Transactional
  public Book createBook(final BookRequest request) {

    final Set<Category> categories = getCategories(request.getCategories());
    final Set<Author> authors = getAuthors(request.getAuthors());

    final Book book =
        Book.createNewBook(
            request.getTitle(),
            request.getPages(),
            request.getIsbn(),
            request.getPrice(),
            request.getSummary(),
            request.getEditorial(),
            request.getDatePublication(),
            categories,
            authors);

    bookDAO.create(book);
    return book;
  }

  @Transactional
  public void updateBook(final int bookId, final BookRequest request) {

    final Book book = bookDAO.findById(bookId);
    if (book == null) {
      throw new ResourceNotFoundException("Book not found: " + bookId);
    }

    final Set<Category> categories = getCategories(request.getCategories());
    final Set<Author> authors = getAuthors(request.getAuthors());

    book.update(request, categories, authors);

    bookDAO.update(book);
  }

  @Transactional
  public void deleteBook(final int bookId) {
    final Book book = bookDAO.findById(bookId);
    if (book == null) {
      throw new ResourceNotFoundException("Book not found: " + bookId);
    }

    bookDAO.delete(book);
  }

  private Set<Author> getAuthors(final Set<Integer> authorIds) {
    final Set<Author> authors = new HashSet<>();
    authorIds.forEach(
        authorId -> {
          final Author author = authorDAO.findById(authorId);
          if (author == null) {
            throw new ResourceNotFoundException("Author not found: " + authorId);
          }
          authors.add(author);
        });

    return authors;
  }

  private Set<Category> getCategories(final Set<Integer> categoryIds) {
    final Set<Category> categories = new HashSet<>();
    categoryIds.forEach(
        categoryId -> {
          final Category category = categoryDAO.findById(categoryId);
          if (category == null) {
            throw new ResourceNotFoundException("Category not found: " + categoryId);
          }
          categories.add(category);
        });

    return categories;
  }

  public List<Book> getOrderedBooks(String order, Integer limit, Integer offset) {
    return bookDAO.getBooksByYearOfPublication(order, limit, offset);
  }

  public List<Book> getOrderedBooksByPages(String order, Integer limit, Integer offset) {
    return bookDAO.getBooksByPages(order, limit, offset);
  }

  public List<String> getBooksByAuthor(String author) {
    return bookDAO.getBookByAuthor(author);
  }

  public List<String> getBooksByPrice(final BigDecimal priceMin, final BigDecimal priceMax) {
    return bookDAO.getBooksByPrice(priceMin, priceMax);
  }

  public List<String> getBooksByDate(final Date startDate, final Date endDate) {
    return bookDAO.getBooksByDate(startDate, endDate);
  }

  public List<String> getBooksByCategory(final String category) {

    return bookDAO.getBooksByCategory(category);
  }

  public Long getAmountOfBooksByCategory(final String category) {
    return bookDAO.getAmountOfBooksByCategory(category);
  }

  public List<Book> getBooksByAmountAuthors(final long authors) {
    return bookDAO.getBooksByAmountAuthors(authors);
  }

  public List<BookWithRanking> getBooksWithScore(String order, Integer limit, Integer offset) {
    List<Book> books = bookDAO.getBooksByPages(order, limit, offset);
    List<BookWithRanking> booksRankings = new ArrayList<>();

    for (Book book : books) {
      BookWithRanking br = new BookWithRanking();
      Double ranking = bookDAO.getRankingByBook(book.getId());
      br.setScore(ranking);
      booksRankings.add(br);
    }
    return booksRankings;
  }
}
