package logic;

import data.database.MySQLAlbumContext;
import data.database.interfaces.IAlbumContext;
import models.Album;
import models.User;
import models.exceptions.UploadException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tomt on 20-3-2017.
 */
public class CreateAlbumRepo {
    private IAlbumContext context = new MySQLAlbumContext();

    private boolean validateName(final String hex) throws UploadException
    {
        boolean success = (hex.length()>0&&hex.length()<50);
        if (success)
<<<<<<< Updated upstream
            return success;

        throw new UploadException("Invalid album name");
=======
        {
            return true;
        }
        throw new UploadException("Invalid Album name");
>>>>>>> Stashed changes
    }

    public void validateUploadAlbum(Album album, User u) throws UploadException{

        try{
            validateName(album.getName());
            this.context.createAlbum(u.getId(),album.getName());
        } catch (UploadException e)
        {
            e.printStackTrace();
            Logger.getLogger(CreateAlbumRepo.class.getName()).log(Level.INFO, e.getMessage(), e);
            throw e;
        }
    }
}
