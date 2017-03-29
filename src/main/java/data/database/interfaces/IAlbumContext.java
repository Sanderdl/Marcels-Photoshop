package data.database.interfaces;

import models.Album;
import models.GalleryImage;
import models.Photographer;
import models.exceptions.GalleryException;
import models.exceptions.UploadException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by sande on 02/03/2017.
 */
public interface IAlbumContext {
    Collection<Album> getAllAlbumsByUser(Photographer owner) throws UploadException;
    void createAlbum(int accountID, String albumName, int[] categoryIDs, byte[] mainPhotoBytes) throws UploadException;
    Album retrieveAlbumByID(int albumID) throws UploadException;
}
