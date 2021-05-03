package mx.dev.blank.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.dao.AuthorDAO;
import mx.dev.blank.entity.Author;
import mx.dev.blank.entity.Category;
import mx.dev.blank.exception.ResourceNotFoundException;
import mx.dev.blank.web.request.AuthorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class AuthorService {

  private final AuthorDAO authorDAO;

  /*CRUD*/

  @Transactional
  public void createAuthor(final AuthorRequest request) {
    final Author author =
        Author.newAuthor(
            request.getName(),
            request.getFirstName(),
            request.getSecondName(),
            request.getBirthday());
    authorDAO.create(author);
  }

  @Transactional
  public void updateAuthor(final int authorId, final AuthorRequest request) {

    final Author author = authorDAO.findById(authorId);
    if (author == null) {
      throw new ResourceNotFoundException("Author not found: " + authorId);
    }

    author.update(request);

    authorDAO.update(author);
  }

  @Transactional
  public void deleteAuthor(final int authorId) {
    final Author author = authorDAO.findById(authorId);
    if (author == null) {
      throw new ResourceNotFoundException("Author not found: " + authorId);
    }

    authorDAO.delete(author);
  }

  @Transactional(readOnly = true)
  public List<Author> findByBookId(final int bookId) {
    return authorDAO.findByBookId(bookId);
  }

  @Transactional(readOnly = true)
  public List<Author> findAll() {
    return authorDAO.findAll();
  }


  @Transactional(readOnly = true)
  public Author findByAuthorId(final int authorId) {
    return authorDAO.findById(authorId);
  }
}
