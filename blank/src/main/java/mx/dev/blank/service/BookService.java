package mx.dev.blank.service;

import lombok.RequiredArgsConstructor;
import mx.dev.blank.dao.BookJpaDAO;
import mx.dev.blank.entity.Book;
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
}
