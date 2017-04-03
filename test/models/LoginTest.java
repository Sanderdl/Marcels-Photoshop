package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adriaan on 03-Apr-17.
 */
public class LoginTest {
    Login l;

    @Test
    public void loginTest(){
        assertTrue(l == null);
        l = new Login();

        assertTrue(l.getUsername() == null);
        l.setUsername("foo");
        assertTrue(l.getUsername() == "foo");

        assertTrue(l.getPassword() == null);
        l.setPassword("foo");
        assertTrue(l.getPassword() == "foo");


    }

}