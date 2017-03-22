package data.database.interfaces;

import models.Album;
import models.GalleryImage;
import models.Photographer;
import models.exceptions.GalleryException;
import models.exceptions.UploadException;

import java.util.Collection;
import java.util.Map;

/**
 * Created by sande on 02/03/2017.
 */
public interface IAlbumContext {
    GalleryImage getImageById(int id) throws GalleryException;
    Map<Integer, GalleryImage> allImages(int pageNumber) throws GalleryException;
    int getImageCount();
    Collection<Album> getAllAlbumsByUser(Photographer owner) throws UploadException;
    void createAlbum(int accountID, String albumName) throws UploadException;
}
