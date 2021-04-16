package mx.dev.blank.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.beust.jcommander.internal.Lists;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {DAOTestConfig.class, DBTestConfig.class})
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  DbUnitTestExecutionListener.class
})
public class BookByCategoryDAOTest extends AbstractTransactionalTestNGSpringContextTests {
  private static final String DBUNIT_XML = "classpath:dbunit/dao/data.xml";

  @Autowired private BookDAO bookDAO;

  @DataProvider
  public Object[][] findBookByCategory_dataProvider() {
    List<String> namesBook = Lists.newArrayList("title 2", "title 4");

    return new Object[][] {{"drama", namesBook}};
  }

  @Test(dataProvider = "findBookByCategory_dataProvider")
  @DatabaseSetup(DBUNIT_XML)
  public void getBooksByCategory(final String category, final List<String> nameBooks) {
    final List<String> books = bookDAO.getBooksByCategory(category);

    assertThat(books).isNotNull();
    assertThat(books).isEqualTo(nameBooks);
  }
}
