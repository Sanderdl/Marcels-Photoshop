package models;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by lucreinink on 08/03/2017.
 */
public class UserTest extends TestCase {

    private User user;

    @Test
    public void testCreateNewUserType() {

        user = User.createNewUser(10, "Fred Frans", "Fred", "fred@fred.nl", "customer");
        assertEquals(user.getClass().getName().toString(), "models.Customer");

        user = User.createNewUser(20, "Hans Frans", "Hans", "hans@hans.nl", "photographer");
        assertEquals(user.getClass().getName().toString(), "models.Photographer");
    }

}