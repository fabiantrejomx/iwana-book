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

    @Transactional
    public Book findBook(final long bookId) {
        final Book book = bookDAO.findById(bookId);
        if(book == null) {
            throw new ResourceNotFound("Book not found: " + bookId);
        }
        return book;
    }

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
        bookDAO.delete(book);

        log.info("Delete book successful: {}", book);
    }

    //1

    @Transactional
    public List<Book> getBooksListByPublicationDateOrderByAsc(final BookFilterRequest request) {
        return bookDAO.getBooksByPublicationDateAsc(request);
    }

    //2

    @Transactional
    public List<Book> getBooksListByPublicationDateOrderByDesc(final BookFilterRequest request) {
        return bookDAO.getBooksByPublicationDateDesc(request);
    }

    //3

    @Transactional
    public List<Book> getBooksListByAuthorNameOrLastname(final BookFilterRequest request) {
        return bookDAO.getBooksByAuthorNameOrLastname(request.getNameOrLastname());
    }

    //4

    @Transactional
    public List<Book> getBooksListByMinAndMaxPrice(final BookFilterRequest request) {
        return bookDAO.getBooksByMinAndMaxPrice(request.getMinPrice(), request.getMaxPrice());
    }

    //5

    //6

    @Transactional
    public List<Book> getBooksListByInitAndFinalPublicationDate(final BookFilterRequest request) {
        return bookDAO.getBooksByInitAndFinalPublicationDate(request.getInitialPublicationDate(),
                request.getFinalPublicationDate());
    }

    //7

    @Transactional
    public int countBooksByXCategory(final String category) {
        return bookDAO.countBooksByXCategory(category);
    }

    //8

    @Transactional
    public List<Book> getBooksListByCategory(final BookFilterRequest request) {
        return bookDAO.getBooksByCategory(request);
    }

    //9

    @Transactional
    public List<Book> getBooksListByPagesNumberOrderByAsc(final BookFilterRequest request) {
        return bookDAO.getBooksByPageNumberAsc(request);
    }

    @Transactional
    public List<Book> getBooksListByPagesNumberOrderByDesc(final BookFilterRequest request) {
        return bookDAO.getBooksByPageNumberDesc(request);
    }


}
