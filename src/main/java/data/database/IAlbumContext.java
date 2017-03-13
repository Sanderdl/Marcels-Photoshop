package data.database;

import models.GalleryImage;

import java.util.HashSet;

/**
 * Created by sande on 02/03/2017.
 */
public interface IAlbumContext {
    GalleryImage getImageById(int id);
    HashSet<Integer> allImages();
}
