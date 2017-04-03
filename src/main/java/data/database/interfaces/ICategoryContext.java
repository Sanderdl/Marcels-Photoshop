package data.database.interfaces;

import models.AlbumCategory;
import models.exceptions.AlbumException;

import java.util.Collection;
import java.util.List;

/**
 * Created by Adriaan on 29-Mar-17.
 */
public interface ICategoryContext {

    /**
     * Retrieves a list of all available categories applicable to albums.
     * @return A list of all available categories as Collection<AlbumCategory>
     * @throws AlbumException for any error encountered while retrieving values
     */
    Collection<AlbumCategory> getAllCategories() throws AlbumException;

    /**
     * Retrieves a list of all categories applicable to a specific album.
     * @param albumId The Id of an album. Used to retrieve all its category tags.
     * @return A list of all available categories as Collection<AlbumCategory>
     * @throws AlbumException for any error encountered while retrieving values.
     */
    Collection<AlbumCategory> getCategoryForAlbum(int albumId) throws AlbumException;

    /**
     * Adds every given AlbumCategory to the Album specified album in the database.
     * ASSUMES albumList DOES NOT CONTAIN existing category entries.
     * @param albumList A collection of AlbumCategory, used to determine which categories are added.
     * @param albumId The Id of an album.
     * @throws AlbumException for any error encountered while setting values.
     */
    void addCategoryToAlbum(Collection<AlbumCategory> albumList, int albumId) throws AlbumException;
}
