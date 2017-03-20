package data.database.interfaces;

import models.Extra;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by sande on 13/03/2017.
 */
public interface IExtrasContext {
    Collection<Extra> getAvailableExtras() throws SQLException;

    void registerExtras(int imageID, Extra[] extras) throws SQLException;
}


