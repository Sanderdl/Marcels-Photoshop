package logic;

import data.database.MySQLAlbumContext;
import data.database.interfaces.IAlbumContext;
import models.Album;
import models.GalleryImage;
import models.User;
import models.exceptions.AlbumException;
import models.exceptions.GalleryException;
import models.exceptions.UploadException;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tomt on 20-3-2017.
 */
public class AlbumRepo {
    private IAlbumContext context = new MySQLAlbumContext();

    public void validateUploadAlbum(Album album, User u) throws UploadException{

        try
        {
            byte[] bytes = album.getThumbnail().getBytes();
            validateFileSize(bytes);
            if(validateName(album.getName())){
                this.context.createAlbum(u.getId(), album.getName(), album.getCategories(), bytes);
            }
        }
//        catch (UploadException e)
//        {
//            e.printStackTrace();
//            Logger.getLogger(AlbumRepo.class.getName()).log(Level.INFO, e.getMessage(), e);
//            throw e;
//        }
        catch(IOException e){
            Logger.getLogger(AlbumRepo.class.getName()).log(Level.INFO, e.getMessage(), e);
            throw new UploadException("An error occurred while retrieving the size of your picture, please try again.");
        }
    }

    public Album retrieveAlbumById(int albumId) throws UploadException{
        try {
            return context.retrieveAlbumByID(albumId);
        } catch (UploadException e) {
            Logger.getLogger(AlbumRepo.class.getName()).log(Level.INFO, e.getMessage(), e);
            throw new UploadException("An error occurred while retrieving your picture, please try again.");
        }
    }

    private void validateFileSize(byte[] bytes) throws UploadException{

        if (bytes.length > 64000)
            throw new UploadException("The chosen picture is too large. The maximum permitted size is 64kb");
    }

    private boolean validateName(final String hex) throws UploadException
    {
        boolean success = (hex.length()>0&&hex.length()<50);
        if (success) return success;

        throw new UploadException("The title of your album must be between 1 and 50 characters long.");
    }

    public Map<Integer, GalleryImage> retrieveAlbumPictures(int id) throws AlbumException, UploadException, GalleryException {
        Map<Integer,GalleryImage> map = null;
        try {
            map = context.retrieveImagesForAlbum(id);
        } catch (GalleryException e) {
            Logger.getLogger(AlbumRepo.class.getName()).log(Level.INFO, e.getMessage(), e);
            throw new GalleryException("Not all album images loaded");
        }
        return map;
    }
}
