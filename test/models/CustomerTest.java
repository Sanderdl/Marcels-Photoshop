package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adriaan on 03-Apr-17.
 */
public class CustomerTest {

    @Test
    public void customerConstructorTest(){
        Customer a = new Customer(-1, "userName", "name", "email", User.UserStatus.verified);
        assertTrue(a != null);

    }

}