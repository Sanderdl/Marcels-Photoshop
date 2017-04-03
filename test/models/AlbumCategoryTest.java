package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adriaan on 03-Apr-17.
 */
public class AlbumCategoryTest {
    private AlbumCategory ac;

    @Test
    public void albumCategoryConstructorTest(){
        assertTrue(ac == null);
        ac = new AlbumCategory(1, "Natuur");

        assertTrue(ac.getCategoryName() == "Natuur");
        assertTrue(ac.getCategoryId() == 1);



    }

}