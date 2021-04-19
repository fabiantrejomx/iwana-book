package mx.dev.blank.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.beust.jcommander.internal.Lists;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.List;
import mx.dev.blank.DAOTestConfig;
import mx.dev.blank.DBTestConfig;
import mx.dev.blank.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {DAOTestConfig.class, DBTestConfig.class})
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  DbUnitTestExecutionListener.class
})
public class BookAuthorDAOTest extends AbstractTransactionalTestNGSpringContextTests {
  private static final String DBUNIT_XML = "classpath:dbunit/dao/data.xml";

  @Autowired private BookDAO bookDAO;

  @DataProvider
  public Object[][] findBookByAuthor_dataProvider() {
    List<String> namesBook = Lists.newArrayList("title 1", "title 2");

    return new Object[][] {{"author-1", namesBook}};
  }

  @Test(dataProvider = "findBookByAuthor_dataProvider")
  @DatabaseSetup(DBUNIT_XML)
  public void getBooksByAuthor(final String author, final List<String> nameBooks) {
    System.out.println(bookDAO);
    final List<Book> books = bookDAO.getBookByAuthor(author);

    assertThat(books).isNotNull();
    assertThat(books).extracting(Book::getTitle).containsAll(nameBooks);
  }
}
