package data.database.interfaces;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by Tomt on 13-3-2017.
 */
public interface IProductContext {
    int uploadPhoto(int ownerid, String name, byte[] photoblob, double price, boolean ispublic, Date uploaddate) throws SQLException;
    int uploadPhotoWithAlbum(int ownerid, String name, int albumid, byte[] photoblob, double price, boolean ispublic, Date uploaddate) throws SQLException;
}
