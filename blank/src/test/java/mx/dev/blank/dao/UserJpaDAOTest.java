package mx.dev.blank.dao;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import mx.dev.blank.DAOTestConfig;
import mx.dev.blank.DBTestConfig;
import mx.dev.blank.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Test(groups = "db")
@ContextConfiguration(classes = {DAOTestConfig.class, DBTestConfig.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class UserJpaDAOTest extends AbstractTransactionalTestNGSpringContextTests {

    private static final String DBUNIT_XML = "classpath:dbunit/dao/user-data.xml";

    @Autowired
    private UserJpaDAO userJpaDAO;

    @DataProvider
    private Object[][] findByID_dataProvider() {

        return new Object[][] {
                {"1", "1"},
                {null, "2"}
        };
    }

    @Test(dataProvider = "findByID_dataProvider")
    @DatabaseSetup(DBUNIT_XML)
    private void findByIDTest(final String id, final String expectedID) {

        final User result = userJpaDAO.getById(id);
        if (expectedID != null) {
            assertThat(result.getId()).isEqualTo(expectedID);
        } else {
            assertThat(result).isNull();
        }
    }


}
