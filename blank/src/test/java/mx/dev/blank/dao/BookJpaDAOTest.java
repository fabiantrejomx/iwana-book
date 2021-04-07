package mx.dev.blank.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import mx.dev.blank.DAOTestConfig;
import mx.dev.blank.DBTestConfig;
import mx.dev.blank.entity.Book;
import mx.dev.blank.entity.User;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {DAOTestConfig.class, DBTestConfig.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class BookJpaDAOTest extends AbstractTransactionalTestNGSpringContextTests{
    private static final String DBUNIT_XML = "classpath:dbunit.dao/user.xml";

    @Autowired
    private BookDAO bookDAO;

    @DataProvider
    private Object[][] getAll_dataProvider() {

        return new Object[][] {
                {Lists.newArrayList("100 años de soledad","El amor en los tiempos de colera")}
        };
    }

    @Test(dataProvider = "getAll_dataProvider")
    @DatabaseSetup(DBUNIT_XML)
    private void getAll_Test(final List<String> expectedbooks) {

        final List<Book> books = bookDAO.getAll();
        assertThat(books).extracting(Book::getTitle).hasSameElementsAs(expectedbooks);

    }


}
