package mx.dev.blank.service;

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

    @Transactional
    public List<Book> getBooksListByPublicationDateOrderByAsc(final BookFilterRequest request) {
        return bookDAO.getBooksByPublicationDateAsc(request);
    }

    @Transactional
    public List<Book> getBooksListByPublicationDateOrderByDesc(final BookFilterRequest request) {
        return bookDAO.getBooksByPublicationDateDesc(request);
    }

    @Transactional
    public List<Book> getBooksListByMinAndMaxPrice(final BookFilterRequest request) {
        return bookDAO.getBooksByMinAndMaxPrice(request);
    }

    @Transactional
    public List<Book> getBooksListByInitAndFinalPublicationDate(final BookFilterRequest request) {
        return bookDAO.getBooksByInitAndFinalPublicationDate(request);
    }

}
