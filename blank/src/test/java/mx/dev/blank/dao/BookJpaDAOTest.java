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
                {"Test case all titles the books",Lists.newArrayList("100 años de soledad","El amor en los tiempos de colera","y colorin colorado","y si nos tomamos un cafe?")}
        };
    }

    @Test(dataProvider = "getAll_dataProvider")
    @DatabaseSetup(DBUNIT_XML)
    private void getAll_Test(final String testCase,final List<String> expectedbooks) {

        final List<Book> books = bookDAO.getAll();
        assertThat(books).extracting(Book::getTitle).hasSameElementsAs(expectedbooks);

    }

    @DataProvider
    private Object[][] getAllOrderAsc_dataProvider() {

        return new Object[][] {
                {"Test case all by pagination",
                        "asc",
                        2,
                        0,
                        Lists.newArrayList("y colorin colorado","y si nos tomamos un cafe?")}

        };
    }

    @Test(dataProvider = "getAllOrderAsc_dataProvider")
    @DatabaseSetup(DBUNIT_XML)
    private void getAllOrderAsc_Test(final String testCase,final String order,final Integer limit,final Integer offset,final List<String> expectedbooks) {

        final List<Book> books = bookDAO.getAllOrderAsc(order,limit,offset);
        assertThat(books).extracting(Book::getTitle).hasSameElementsAs(expectedbooks);

    }

    @DataProvider
    private Object[][] getAllOrderDesc_dataProvider() {

        return new Object[][] {
                {"Test case all by pagination",
                        "desc",
                        2,
                        0,
                        Lists.newArrayList("y si nos tomamos un cafe?","y colorin colorado")}

        };
    }

    @Test(dataProvider = "getAllOrderDesc_dataProvider")
    @DatabaseSetup(DBUNIT_XML)
    private void getAllOrderDesc_Test(final String testCase,final String order,final Integer limit,final Integer offset,final List<String> expectedbooks) {

        final List<Book> books = bookDAO.getAllOrderAsc(order,limit,offset);
        assertThat(books).extracting(Book::getTitle).hasSameElementsAs(expectedbooks);

    }


}
