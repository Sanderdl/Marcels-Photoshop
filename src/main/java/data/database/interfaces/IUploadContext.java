package data.database.interfaces;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by Tomt on 13-3-2017.
 */
public interface IUploadContext {
    void uploadPhoto(int ownerid, String name, int albumid, Byte[] photoblob, double price, int ispublic, Date uploaddate) throws SQLException;
}
