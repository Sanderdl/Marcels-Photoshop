package data.database.interfaces;

import models.Extra;
import models.exceptions.ExtraException;
import models.exceptions.UploadException;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by sande on 13/03/2017.
 */
public interface IExtrasContext {
    Collection<Extra> getAvailableExtras() throws UploadException;
    void registerExtras(int imageID, int[] extras) throws SQLException;
    void addNewExtraProduct(String name, double price, boolean available) throws UploadException;
    void changeAvailable(int id, boolean available) throws UploadException;
    void deleteExtra(int id) throws ExtraException;
    void changePrice(int id, double price) throws UploadException;
}


