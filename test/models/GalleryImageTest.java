package models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Adriaan on 03-Apr-17.
 */
public class GalleryImageTest {
    GalleryImage gi;

    @Test
    public void galleryImageConstructorTest(){
        assertTrue(gi == null);
        gi = new GalleryImage(-1, "boo", new byte[]{}, 1, "fred");

        assertTrue(gi.getId() == -1);
        assertTrue(gi.getName() == "boo");
        assertTrue(gi.getImage().length == 0);
        assertTrue(gi.getPhotographerId() == 1);
        assertTrue(gi.getPhotographerName() == "fred");
    }

}