package mx.dev.blank.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.util.List;
import mx.dev.blank.DAOTestConfig;
import mx.dev.blank.DBTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {DAOTestConfig.class, DBTestConfig.class})
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  DbUnitTestExecutionListener.class
})
public class BooksByAmountAuthorsDAOTest extends AbstractTransactionalTestNGSpringContextTests {
  private static final String DBUNIT_XML = "classpath:dbunit/dao/data.xml";
  @Autowired private BookDAO bookDAO;

  @Test
  @DatabaseSetup(DBUNIT_XML)
  public void getBooksByAmountAuthors() {

    final List<Integer> books = bookDAO.getBooksByAmountAuthors(2);
    System.out.println(books.size());

    assertThat(books).isNotNull();
  }
}
