package logic;

import data.database.interfaces.ILoginContext;
import models.Customer;
import models.User;
import models.exceptions.LoginException;
import org.junit.Before;
import org.junit.Test;

import javax.management.relation.Role;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Adriaan on 12-Mar-17.
 */
public class LoginRepoTest {
    private LoginRepo repo;
    String tooLong;
    @Before
    public void setUp() throws Exception {
        repo = new LoginRepo(new TestLoginContext());
        tooLong = generateString(55);

    }

    @Test
    public void invalidPasswordLength() throws LoginException {
        try{
            // password too short
            repo.UserLogin("123", "123");
            fail("Password is too short");
        }
        catch(LoginException ex){
            assertEquals("The length of the entered password must be between 8 and 50 characters", ex.getMessage());
            // password too long
            try{
                repo.UserLogin("123", tooLong);
                fail("Password is too long");
            }
            catch(LoginException ex1){
                assertEquals("The length of the entered password must be between 8 and 50 characters", ex1.getMessage());
            }
        }
    }

    @Test
    public void invalidUsernameLength() throws LoginException {
        try{
            // password too short
            repo.UserLogin("1", "12345678");
            fail("Username is too short");
        }
        catch(LoginException ex){
            assertEquals("The length of the entered username must be between 3 and 50 characters", ex.getMessage());
            // password too long
            try{
                repo.UserLogin(tooLong, "12345678");
                fail("Username is too long");
            }
            catch(LoginException ex1){
                assertEquals("The length of the entered username must be between 3 and 50 characters", ex1.getMessage());
            }
        }
    }

    @Test
    public void loginTest() throws LoginException{
        try{
            // invalid login
            repo.UserLogin("Bob", "12345678");
            fail("Invalid login succeeded");
        }
        catch(LoginException ex){
            try{
                // valid login
                repo.UserLogin("Fred", "12345678");
            }
            catch (LoginException ex1){
                fail("Valid login failed");
            }
        }
    }

    private String generateString(int length){
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++){
            sb.append("1");
        }
        return sb.toString();
    }

    private enum RoleType{
        Client, Photographer, Admin
    }

    class TestLoginContext implements ILoginContext{
        private HashMap<String, String> map = new HashMap<String, String>();

        public TestLoginContext(){
            map.put("Fred", "12345678");
        }

        @Override
        public User userLogin(String username, String password) throws LoginException {
            if(!map.containsKey(username)){
                // Must fail
                throw new LoginException("");
            }
            else if(map.containsKey(username) && map.get(username) == password){
                // Success, return some user
                return new Customer(1, "Name", "Username", "mail@mail.com");
            }
            // Wrong password, in this test, do nothing
            return null;
        }
    }

}