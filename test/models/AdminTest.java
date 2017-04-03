package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adriaan on 03-Apr-17.
 */
public class AdminTest {

    @Test
    public void adminConstructorTest(){
        Admin a = new Admin(-1, "userName", "name", "email", User.UserStatus.verified);
        assertTrue(a != null);

    }


}