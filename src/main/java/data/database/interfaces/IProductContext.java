package data.database.interfaces;

import models.Extra;
import models.GalleryImage;

import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by Tomt on 13-3-2017.
 */
public interface IProductContext {
    void uploadPhoto(int ownerid, String name, int albumid, byte[] photoblob, double price, boolean ispublic, Date uploaddate) throws SQLException;

    void registerExtras(GalleryImage image, Extra extra);
}
