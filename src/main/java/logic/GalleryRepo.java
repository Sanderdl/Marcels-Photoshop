package logic;

import data.database.MySQLGalleryContext;
import data.database.interfaces.IGalleryContext;
import models.GalleryImage;
import models.Photographer;
import models.exceptions.GalleryException;
import models.exceptions.PageOutOfBoundsException;

import java.util.Map;

/**
 * Created by Adriaan on 22-Mar-17.
 */
public class GalleryRepo {

    private IGalleryContext context;

    public GalleryRepo() {
        this.context = new MySQLGalleryContext();
    }

    public Map<Integer, GalleryImage> allImages(int pageNumber) throws GalleryException, PageOutOfBoundsException {
        if (pageNumber <= 0) {
            throw new PageOutOfBoundsException("Always be positive about your numbers");
        }
        int numberPage = (pageNumber - 1) * 24;

        return context.allImages(numberPage);
    }

    public GalleryImage getImageById(int id) throws GalleryException {

        if (id >= 0) {
            return context.getImageById(id);
        }

        return null;
    }

    public int getPageCount() {
        double result = context.getHomePageCount();
        return (int) java.lang.Math.ceil(result);
    }

    public Photographer getPhotographerByImageId(int imageId) {
        return this.context.getPhotographerByImageId(imageId);
    }

    public Map<Integer, GalleryImage> allSharedImages(int sharedWithId, int pageNumber)
            throws GalleryException, PageOutOfBoundsException {
        if (pageNumber <= 0) {
            throw new PageOutOfBoundsException("Always be positive about your numbers");
        }
        int numberPage = (pageNumber - 1) * 24;

        return this.context.allSharedImages(sharedWithId, numberPage);
    }

    public int getPrivatePageCount(int accountID) {
        double result = context.getPrivatePageCount(accountID);
        return (int) java.lang.Math.ceil(result);
    }
}
