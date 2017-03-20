package data.database.interfaces;

import models.Album;
import models.GalleryImage;
import models.Photographer;
import models.exceptions.GalleryException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by sande on 02/03/2017.
 */
public interface IAlbumContext {
    GalleryImage getImageById(int id) throws SQLException;
    Map<Integer, GalleryImage> allImages() throws GalleryException;
    Collection<Album> getAllAlbumsByUser(Photographer owner) throws SQLException;
    void createAlbum(int accountID, String albumName) throws SQLException;
}
