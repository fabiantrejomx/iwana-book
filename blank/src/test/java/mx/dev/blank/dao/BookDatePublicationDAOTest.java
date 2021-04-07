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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@ContextConfiguration(classes = {DAOTestConfig.class, DBTestConfig.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})

public class BookDatePublicationDAOTest extends AbstractTransactionalTestNGSpringContextTests {
    private static final String DBUNIT_XML = "classpath:dbunit/dao/data.xml";
    @Autowired
    private BookDAO bookDAO;

    @DataProvider
    public Object[][] findBooksByDate_dataProvider() throws ParseException {
        List<String> namesBook = Lists.newArrayList("title 1", "title 2");
        String start = "1955-01-01";
        String end = "1961-01-01";

        DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = date.parse(start);
        System.out.println(startDate);

        DateFormat dateTwo = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate = dateTwo.parse(end);
        System.out.println(endDate);

        return new Object[][] {
                {startDate, endDate, namesBook}

        };
    }
    @Test(dataProvider = "findBooksByDate_dataProvider")
    @DatabaseSetup(DBUNIT_XML)
    public void getBooksbyDate(final Date startDate, final Date endDate, final List<String> nameBook) {
        System.out.println(startDate);
        final List<String> date = bookDAO.getBooksByDate(startDate, endDate);

        assertThat(date).isNotNull();
        assertThat(date).isEqualTo(nameBook);

    }

}
