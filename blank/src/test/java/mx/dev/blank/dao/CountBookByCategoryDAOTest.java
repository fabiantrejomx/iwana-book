package mx.dev.blank.dao;

import com.beust.jcommander.internal.Lists;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import mx.dev.blank.DAOTestConfig;
import mx.dev.blank.DBTestConfig;
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

public class CountBookByCategoryDAOTest extends AbstractTransactionalTestNGSpringContextTests {
    private static final String DBUNIT_XML = "classpath:dbunit/dao/data.xml";

    @Autowired
    private BookDAO bookDAO;

    @DataProvider
    public Object[][] findAmountBookByCategory_dataProvider() {

        return new Object[][] {
                {"drama", 2}
        };
    }

    @Test(dataProvider = "findAmountBookByCategory_dataProvider")
    @DatabaseSetup(DBUNIT_XML)
    public void getAmountBooksByCategory(final String category, final long amount ) {
        final Long books = bookDAO.getCountBooksByCategory(category);

        assertThat(books).isNotNull();
        assertThat(books).isEqualTo(amount);

    }

}
