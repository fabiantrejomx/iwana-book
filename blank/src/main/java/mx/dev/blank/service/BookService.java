package mx.dev.blank.service;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.dao.BookJpaDAO;
import mx.dev.blank.entity.Book;
import mx.dev.blank.web.request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    @Autowired
    BookJpaDAO bookJpaDAO;

    public List<Book> getOrderedBooks(String order, Integer limit, Integer offset){
        return bookJpaDAO.getBooksByYearOfPublication(order, limit, offset);
    }

    public List<Book> getOrderedBooksByPages(String order, Integer limit, Integer offset){
        return bookJpaDAO.getBooksByPages(order, limit, offset);
    }

    public List<String> getBooksByAuthor(String author){
        return bookJpaDAO.getBookByAuthor(author);
    }

    public List<String> getBooksByPrice(
            final float priceMin,
            final float priceMax){
        return bookJpaDAO.getBooksByPrice(priceMin, priceMax);
    }

    public List<String> getBooksByDate(
            final Date startDate,
            final Date endDate){
        return bookJpaDAO.getBooksByDate(startDate, endDate);
    }

    public List<String> getBooksByCategory(final String category){
        return bookJpaDAO.getBooksByCategory(category);
    }

    public Long getAmountOfBooksByCategory(final String category){
        return bookJpaDAO.getAmountOfBooksByCategory(category);
    }

    public List<Book> getBooksByAmountAuthors(final long authors){
        return bookJpaDAO.getBooksByAmountAuthors(authors);
    }

    @Transactional
    public Book createBook(final BookRequest bookRequest){
        Book book = bookJpaDAO.createBook(bookRequest);
        return book;
    }

    @Transactional
    public Integer updateBook(final Integer bookId, final BookRequest bookRequest){
        Integer isUpdated = bookJpaDAO.updateBook(bookId, bookRequest);
        return isUpdated;
    }

    @Transactional
    public Integer deleteBook(final int bookId){
        Integer isDelete = bookJpaDAO.deleteBook(bookId);
        return isDelete;
    }

}
