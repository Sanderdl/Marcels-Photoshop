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
    /**
     *
     * @param owner The name of the photographer. Used to retrieve all its album tags
     * @return A collection which contains albums from a specific user.
     * @throws UploadException for any error encountered while retrieving values
     */
    Collection<Album> getAllAlbumsByUser(Photographer owner) throws UploadException;

    /**
     *
     * @param accountID The id of an user
     * @param albumName The name of an album
     * @param categoryIDs An int array which contains all the category's id's
     * @param mainPhotoBytes An byte array
     * @throws UploadException for any error encountered while retrieving values
     */
    void createAlbum(int accountID, String albumName, int[] categoryIDs, byte[] mainPhotoBytes) throws UploadException;

    /**
     *
     * @param albumID The id of an album. Used to retrieve all its albums tags
     * @return An album
     * @throws UploadException for any error encountered while retrieving values
     */
    Album retrieveAlbumByID(int albumID) throws UploadException;

    /**
     *
     * @param albumID The id of an album. Used to retrieve all images in its album
     * @return Map with  all images for this album
     * @throws GalleryException for any error encountered while retrieving values
     */
    Map<Integer, GalleryImage> retrieveImagesForAlbum(int albumID) throws GalleryException;
}
