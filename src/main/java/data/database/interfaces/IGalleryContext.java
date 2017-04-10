package data.database.interfaces;

import models.GalleryImage;
import models.Photographer;
import models.exceptions.GalleryException;

import java.util.Map;

/**
 * Created by Adriaan on 22-Mar-17.
 */
public interface IGalleryContext {
    GalleryImage getImageById(int id) throws GalleryException;
    Map<Integer, GalleryImage> allImages(int startIndex) throws GalleryException;

    int getImageCount();

    double getHomePageCount();

    Photographer getPhotographerByImageId(int imageId);

    Map<Integer, GalleryImage> allSharedImages(int sharedWithId, int pageNumber) throws GalleryException;
    double getPrivatePageCount(int accountID);
}
