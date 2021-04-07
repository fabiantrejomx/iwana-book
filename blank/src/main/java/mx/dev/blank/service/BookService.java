package mx.dev.blank.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.dao.BookDAO;
import mx.dev.blank.entity.Book;
import mx.dev.blank.exceptions.ResourceNotFound;
import mx.dev.blank.web.controller.request.BookFilterRequest;
import mx.dev.blank.web.controller.request.BookRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookDAO bookDAO;

    @Transactional(readOnly = false)
    public void addBook(final BookRequest request) {
        Book book = new Book(request.getTitle(), request.getPagesNumber(), request.getAuthors(), request.getEditorial(),
                request.getIsbn(), request.getCategory(), request.getPrice(), request.getSummary(),
                request.getPublicationDate());
        bookDAO.add(book);

        log.info("Create book successful: {}", book);
    }

    @Transactional(readOnly = false)
    public void editBook(final long bookId, final BookRequest request) {
        final Book book = bookDAO.findById(bookId);
        if(book == null) {
            throw new ResourceNotFound("Book not found" + bookId);
        }
        book.update(request);
        bookDAO.update(book);

        log.info("Updating book successful: {}", book);
    }

    @Transactional(readOnly = false)
    public void deleteBook(final long bookId) {
        final Book book = bookDAO.findById(bookId);
        if(book == null) {
            throw new ResourceNotFound("Book not found" + bookId);
        }
        bookDAO.update(book);

        log.info("Delete book successful: {}", book);
    }

    @Transactional
    public Book findBook(final long bookId) {
        final Book book = bookDAO.findById(bookId);
        if(book == null) {
            throw new ResourceNotFound("Book not found: " + bookId);
        }
        return book;
    }

    /*
     * 1.
     * 2.
     * 9.
     */

    @Transactional
    public List<Book> getBooksListByAnyFieldAsc(final BookRequest request, final String field) {
        return bookDAO.getBooksByAnyFieldOrderByAsc(request, field);
    }

    @Transactional
    public List<Book> getBooksListByAnyFieldDesc(final BookRequest request, final String field) {
        return bookDAO.getBooksByAnyFieldOrderByDesc(request, field);
    }

    //3

    @Transactional
    public List<Book> getBooksListByAuthorNameOrLastname(final String nameOrLastname) {
        return bookDAO.getBooksByAuthorNameOrLastname(nameOrLastname);
    }

    //4

    @Transactional
    public List<Book> getBooksListByMinAndMaxPrice(final int minPrice, final int maxPrice) {
        return bookDAO.getBooksByMinAndMaxPrice(minPrice, maxPrice);
    }

    //5

    @Transactional
    public List<Book> getBooksListByXAuthorsNumber(final int authorsNumber) {
        return bookDAO.getBooksByXAuthorsNumber(authorsNumber);
    }

    //6

    @Transactional
    public List<Book> getBooksListByInitAndFinalPublicationDate(final Date initialDate, final Date finalDate) {
        return bookDAO.getBooksByInitAndFinalPublicationDate(initialDate, finalDate);
    }

    //7

    @Transactional
    public int countBooksByXCategory(final String category) {
        return bookDAO.countBooksByXCategory(category);
    }

    //8

    @Transactional
    public List<Book> getBooksListByCategory(final String category) {
        return bookDAO.getBooksByCategory(category);
    }

}
