package data.database;

import data.database.interfaces.ICategoryContext;
import models.AlbumCategory;

import java.util.List;

/**
 * Created by Adriaan on 29-Mar-17.
 */
public class MySQLCategoryContext implements ICategoryContext {


    @Override
    public List<AlbumCategory> getAllCategories() {
        return null;
    }

}
