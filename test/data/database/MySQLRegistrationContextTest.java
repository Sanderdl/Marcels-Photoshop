package data.database;

import data.database.interfaces.IRegistrationContext;
import models.Customer;
import models.Photographer;
import models.Registration;
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
        userContext.registerUser( new Registration("Fred", "FredFred1", "Fred Frans", "fred@fred.nl", "Admin"),"verified");
    }

    @Test(expected = SQLException.class)
    public void registerDuplicateUserTest() throws SQLException {
        userContext.registerUser( new Registration("Fred", "FredFred1", "Fred", "fred@fred.nl", "Customer"),"verified");
        userContext.registerUser( new Registration("Fred", "FredFred1", "Fred", "fred@fred.nl", "Customer"),"verified");
    }


}

class TestRegistrationContext implements IRegistrationContext {

    private final List<User> users;

    public TestRegistrationContext() {
        this.users = new ArrayList<>();
    }

    public void registerUser(Registration registration, String verified) throws SQLException {
        for (User u : this.users) {
            if (u.getUserName().equals(registration.getUserName()) || u.getName().equals(registration.getName())) {
                throw new SQLException("User already registered");
            }
        }
        User user = null;
        if (registration.getRole().equals(User.UserRoles.Customer.toString()))
        {
            user = new Customer(this.users.size() + 1, registration.getUserName(), registration.getName(), registration.getEmail(), User.UserStatus.valueOf(verified));
        }
        else if (registration.getRole().equals(User.UserRoles.Photographer.toString()))
        {
            user = new Photographer(this.users.size() + 1, registration.getUserName(), registration.getName(), registration.getEmail(), User.UserStatus.valueOf(verified));
        }
        users.add(user);
    }
}