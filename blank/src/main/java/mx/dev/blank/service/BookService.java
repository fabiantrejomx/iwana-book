package mx.dev.blank.service;

import lombok.RequiredArgsConstructor;
import mx.dev.blank.dao.BookJpaDAO;
import mx.dev.blank.entity.Book;
import mx.dev.blank.web.request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    @Autowired
    BookJpaDAO bookJpaDAO;

    /*public List<Book> getBooks(){
        return bookJpaDAO.getBooksByYearOfPublication(null);
    }*/

    public List<Book> getOrderedBooks(String order, Integer limit, Integer offset){
        return bookJpaDAO.getBooksByYearOfPublication(order, limit, offset);
    }


    public List<String> getBooksByAuthor(String author){

        return bookJpaDAO.getBookByAuthor(author);
    }

    public List<String> getBooksByPriceMinMax(
            final float priceMin,
            final float priceMax){
        return bookJpaDAO.getBooksByPriceMinMax(priceMin, priceMax);
    }

    public List<String> getBooksByDateStartEnd(
            final Date startDate,
            final Date endDate){
        return bookJpaDAO.getBooksByDateStartEnd(startDate, endDate);
    }

    public List<String> getBooksByCategory(final String category){

        return bookJpaDAO.getBooksByCategory(category);
    }

    public Long getCountBooksByCategory(final String category){

        return bookJpaDAO.getCountBooksByCategory(category);
    }

    public List<Book> getOrderedBooksByPages(String order, Integer limit, Integer offset){
        return bookJpaDAO.getBooksOrderedByPages(order, limit, offset);
    }

    public List<Book> getBooksByCountAuthors(final long numAuthors){
        return bookJpaDAO.getBooksByCountAuthors(numAuthors);
    }

    @Transactional
    public Book createBook(final BookRequest bookRequest){
        Book book = bookJpaDAO.createBook(bookRequest);
        return book;
    }

    @Transactional
    public Integer putBook(final int bookID, final BookRequest bookRequest){
        Integer isPut = bookJpaDAO.putBook(bookID, bookRequest);
        return isPut;
    }

    @Transactional
    public Integer deleteBook(final int bookID, final BookRequest bookRequest){
       Integer isDelete = bookJpaDAO.deleteBook(bookID, bookRequest);
        return isDelete;
    }


}
