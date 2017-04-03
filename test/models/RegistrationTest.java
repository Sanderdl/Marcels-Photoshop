package models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adriaan on 03-Apr-17.
 */
public class RegistrationTest {

    private Registration registration;

    @Before
    public void setup(){

    }

    @Test
    public void RegistrationConstructorTest() {
        assertTrue(registration == null);
        registration = new Registration("userName", "pass", "name", "mail", "Admin");
        assertTrue(registration.getUserName() == "userName");
        registration.setUserName("foo");
        assertTrue(registration.getUserName() == "foo");

        assertTrue(registration.getName() == "name");
        registration.setName("foo");
        assertTrue(registration.getName() == "foo");

        assertTrue(registration.getEmail() == "mail");
        registration.setEmail("foo");
        assertTrue(registration.getEmail() == "foo");

        assertTrue(registration.getRole() == "Admin");
        registration.setRole("foo");
        assertTrue(registration.getRole() == "foo");

        assertTrue(registration.getPassword() == "pass");
        registration.setPassword("foo");
        assertTrue(registration.getPassword() == "foo");

    }

}