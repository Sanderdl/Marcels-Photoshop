package data.database.interfaces;

import models.Extra;
import models.exceptions.UploadException;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by sande on 13/03/2017.
 */
public interface IExtrasContext {
    Collection<Extra> getAvailableExtras() throws UploadException;
    void registerExtras(int imageID, int[] extras) throws SQLException;
    void AddNewExtraProduct(String name, int price, boolean available) throws UploadException;
    void ChangeAvailable(String name, boolean available) throws UploadException;
}


