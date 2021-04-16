package mx.dev.blank.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import java.math.BigDecimal;
import java.util.Arrays;
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
public class BookPriceDAOTest extends AbstractTransactionalTestNGSpringContextTests {
  private static final String DBUNIT_XML = "classpath:dbunit/dao/data.xml";
  @Autowired private BookDAO bookDAO;

  @DataProvider
  public Object[][] findBookByPrice_dataProvider() {
    List<String> namesBook = Arrays.asList("title 1", "title 3");

    return new Object[][] {{new BigDecimal(300), new BigDecimal(500), namesBook}};
  }

  @Test(dataProvider = "findBookByPrice_dataProvider")
  @DatabaseSetup(DBUNIT_XML)
  public void getBooksByPrice(
      final BigDecimal priceMin, final BigDecimal priceMax, final List<String> nameBook) {

    final List<String> price = bookDAO.getBooksByPrice(priceMin, priceMax);

    assertThat(price).isNotNull();
    assertThat(price).isEqualTo(nameBook);
  }
}
