package data.database;

import models.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucreinink on 08/03/2017.
 */
public class MySQLRegistrationContextTest {

    private IRegistrationContext userContext;

    @Before
    public void setup() {
        userContext = new TestRegistrationContext();
    }

    @Test
    public void registerValidUserTest() throws SQLException {
        userContext.registerUser("Fred", "FredFred1", "Fred Frans", "fred@fred.nl", "active", "admin");
    }

    @Test(expected = SQLException.class)
    public void registerDuplicateUserTest() throws SQLException {
        userContext.registerUser("Fred", "FredFred1", "Fred Frans", "fred@fred.nl", "active", "customer");
        userContext.registerUser("Fred", "FredFred1", "Fred Frans", "fred@fred.nl", "active", "customer");
    }


}

class TestRegistrationContext implements IRegistrationContext {

    private final List<User> users;

    public TestRegistrationContext() {
        this.users = new ArrayList<>();
    }

    public void registerUser(String userName, String password, String name, String email, String status, String role) throws SQLException {
        for (User u : this.users) {
            if (u.getUserName().equals(userName) || u.getEmail().equals(email)) {
                throw new SQLException("User already registered");
            }
        }

        this.users.add(User.createNewUser(this.users.size(), userName, password, email, role));
    }
}