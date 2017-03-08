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
public class MySQLUserContextTest {

    private IUserContext userContext;

    @Before
    public void setup() {
        userContext = new MemoryUserContext();
    }

    @Test
    public void registerValidUserTest() throws SQLException {
        userContext.registerUser("Fred", "FredFred1", "Fred Frans", "fred@fred.nl", "active", "admin");
    }

    @Test(expected = SQLException.class)
    public void registerDuplicateUserTest() throws SQLException {
        userContext.registerUser("Fred", "FredFred1", "Fred Frans", "fred@fred.nl", "active", "admin");
        userContext.registerUser("Fred", "FredFred1", "Fred Frans", "fred@fred.nl", "active", "admin");
    }


}

class MemoryUserContext implements IUserContext {

    private final List<User> users;

    public MemoryUserContext() {
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