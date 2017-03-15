package data.database;

import data.database.interfaces.IRegistrationContext;
import models.Customer;
import models.Photographer;
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
        userContext.registerUser("Fred", "FredFred1", "Fred Frans", "fred@fred.nl", "verified", "Admin");
    }

    @Test(expected = SQLException.class)
    public void registerDuplicateUserTest() throws SQLException {
        userContext.registerUser("Fred", "FredFred1", "Fred Frans", "fred@fred.nl", "verified", "Customer");
        userContext.registerUser("Fred", "FredFred1", "Fred Frans", "fred@fred.nl", "verified", "Customer");
    }


}

class TestRegistrationContext implements IRegistrationContext {

    private final List<User> users;

    public TestRegistrationContext() {
        this.users = new ArrayList<>();
    }

    public void registerUser(String userName, String password, String name, String email, String status, String role) throws SQLException {
        for (User u : this.users) {
            if (u.getUserName().equals(userName) || u.getName().equals(name)) {
                throw new SQLException("User already registered");
            }
        }
        User user = null;
        if (role.equals(User.UserRoles.Customer.toString()))
        {
            user = new Customer(this.users.size() + 1, userName, name, email, User.UserStatus.valueOf(status));
        }
        else if (role.equals(User.UserRoles.Photographer.toString()))
        {
            user = new Photographer(this.users.size() + 1, userName, name, email, User.UserStatus.valueOf(status));
        }
        users.add(user);
    }
}