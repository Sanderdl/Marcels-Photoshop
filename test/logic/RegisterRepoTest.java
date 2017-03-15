package logic;

import data.database.interfaces.IRegistrationContext;
import models.*;
import models.exceptions.InvalidRegisterException;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by lucreinink on 13/03/2017.
 */
public class RegisterRepoTest {

    private RegisterRepo registerRepo;

    private Registration registration;

    @Before
    public void setup() {
        this.registerRepo = new RegisterRepo(new TestRegistrationContext());
        this.registration = new Registration("Fred", "FredFred1!", "Fred Frans", "fred@fred.nl", "Customer");
    }

    @Test
    public void registerValidUserTest() throws SQLException, InvalidRegisterException {
        this.registerRepo.registerUser(this.registration);
    }

    @Test(expected = SQLException.class)
    public void registerDuplicateUserTest() throws InvalidRegisterException, SQLException {
        this.registerRepo.registerUser(this.registration);
        this.registerRepo.registerUser(this.registration);
    }

    @Test(expected = InvalidRegisterException.class)
    public void noAtEmailTest() throws SQLException, InvalidRegisterException {

        try {
            this.registerRepo.registerUser(new Registration("Fred", "FredFred1!", "Fred Frans",
                    "fredfrednl", "Customer"));
        } catch (InvalidRegisterException ex) {
            assertTrue(ex.getMessage().toLowerCase().contains("email"));
            throw ex;
        }
    }

    @Test(expected = InvalidRegisterException.class)
    public void noDotEmailTest() throws SQLException, InvalidRegisterException {

        try {
            this.registerRepo.registerUser(new Registration("Fred", "FredFred1!", "Fred Frans",
                    "fred@frednl", "Customer"));
        } catch (InvalidRegisterException ex) {
            assertTrue(ex.getMessage().toLowerCase().contains("email"));
            throw ex;
        }
    }

    @Test
    public void invalidNameTest() throws SQLException, InvalidRegisterException {

        try {
            this.registerRepo.registerUser(new Registration("F", "FredFred1!", "Fred Frans",
                    "fred@fred.nl", "Customer"));
        } catch (InvalidRegisterException ex) {
            assertTrue(ex.getMessage().toLowerCase().contains("name"));
        }
    }

    @Test
    public void invalidPasswordTest() throws SQLException, InvalidRegisterException {

        try {
            this.registerRepo.registerUser(new Registration("Fred", "FredFred1", "Fred Frans",
                    "fred@fred.nl", "Customer"));
        } catch (InvalidRegisterException ex) {
            assertTrue(ex.getMessage().toLowerCase().contains("password"));
        }
    }

    class TestRegistrationContext implements IRegistrationContext {

        public final List<User> users;

        public TestRegistrationContext() {
            this.users = new ArrayList<>();
        }

        public void registerUser(String userName, String password, String name, String email, String status, String role) throws SQLException {
            for (User u : this.users)
            {
                if (u.getUserName().equals(userName) || u.getEmail().equals(email))
                {
                    throw new SQLException("User already registered");
                }

            }
            int id = users.size() + 1;
            User newUser = null;
            switch (role)
            {
                case "Customer":
                    newUser = new Customer(id, userName, name, email, User.UserStatus.valueOf(status));
                case "Photographer":
                    newUser = new Photographer(id, userName, name, email, User.UserStatus.valueOf(status));
                case "Admin":
                    newUser = new Admin(id, userName, name, email, User.UserStatus.valueOf(status));
                default:
            }
            if (newUser != null) users.add(newUser);


        }


    }
}