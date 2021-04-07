package mx.dev.blank.service;

import lombok.RequiredArgsConstructor;
import mx.dev.blank.dao.BookJpaDAO;
import mx.dev.blank.entity.Book;
import mx.dev.blank.web.form.BookForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    @Autowired
    final BookJpaDAO bookJpaDAO;

    public List<Book> getAll(){
        return bookJpaDAO.getAll();
    }

    public List<Book> getAllOrderAsc(final String order,final Integer limit,final Integer offset){
        return bookJpaDAO.getAllOrderAsc(order,limit,offset);
    }

    public List<Book> getAllOrderDesc(final String order,final Integer limit,final Integer offset){
        return bookJpaDAO.getAllOrderDesc(order,limit,offset);
    }

    public Book createBook(final BookForm form){

        final Book book =
                new Book(
                        form.getId(),
                        form.getTitle(),
                        form.getNumPages(),
                        form.getCategoryID(),
                        form.getEditorID(),
                        form.getIsbn(),
                        form.getPrice(),
                        form.getResume(),
                        form.getDatePublished()
                        );

            return book;
    }
}
