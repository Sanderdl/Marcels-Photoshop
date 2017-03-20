package logic;

import data.database.MySQLAlbumContext;
import data.database.interfaces.IAlbumContext;
import models.Album;
import models.User;
import models.exceptions.UploadException;

import java.io.IOError;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Tomt on 20-3-2017.
 */
public class CreateAlbumRepo {
    private IAlbumContext context = new MySQLAlbumContext();

    private boolean validateName(final String hex) throws UploadException
    {
        boolean success = (hex.length()>0&&hex.length()<50);
        if (success)
        {
            return true;
        }
        throw new UploadException("Invalid album name");
    }

    public void validateUploadAlbum(Album album, User u) throws UploadException, SQLException{
        validateName(album.getName());
        try{
            this.context.createAlbum(u.getId(),album.getName());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
