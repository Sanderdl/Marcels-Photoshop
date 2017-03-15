package data.database.interfaces;

import models.Album;
import models.GalleryImage;
import models.Photographer;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by sande on 02/03/2017.
 */
public interface IAlbumContext {
    GalleryImage getImageById(int id) throws SQLException;
    HashSet<Integer> allImages() throws SQLException;
    Collection<Album> getAllAlbumsByUser(Photographer owner) throws SQLException;
}
