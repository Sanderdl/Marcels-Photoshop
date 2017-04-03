package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adriaan on 03-Apr-17.
 */
public class ExtraTest {
    Extra e;

    @Test
    public void extraConstructorTest(){
        assertTrue(e == null);
        e = new Extra(-1, "Shirt", 1.00, true);

        assertTrue(e.getId() == -1);
        e.setId(1);
        assertTrue(e.getId() == 1);

        assertTrue(e.getName() == "Shirt");
        e.setName("foo");
        assertTrue(e.getName() == "foo");

        assertTrue(e.getPrice() == 1.00);
        e.setPrice(2.00);
        assertTrue(e.getPrice() == 2.00);

        assertTrue(e.getAvailable() == true);
        e.setAvailable(false);
        assertTrue(e.getAvailable() == false);

        assertTrue(e.toString().equals(e.getName()));

    }

}