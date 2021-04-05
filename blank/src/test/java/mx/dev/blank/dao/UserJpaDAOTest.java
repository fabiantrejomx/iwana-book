package mx.dev.blank.dao;

import static org.assertj.core.api.Assertions.assertThat;
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

@ContextConfiguration(classes = {DAOTestConfig.class, DBTestConfig.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class UserJpaDAOTest extends AbstractTransactionalTestNGSpringContextTests {

    private static final String DBUNIT_XML = "classpath:dbunit/dao/data.xml";

    @Autowired
    private UserDAO userDAO;

    @DataProvider
    public Object[][] getById_dataProvider() {
        return new Object[][] {
                {"1", "Usuario 1"},
        };
    }

    @Test(dataProvider = "getById_dataProvider")
    @DatabaseSetup(DBUNIT_XML)
    public void getById_should_returnTeacher(final String userID, final String userName) {
        final User user= userDAO.getById(userID);

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo(userName);

    }
}
