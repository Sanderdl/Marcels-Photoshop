package logic;

import data.database.MySQLCategoryContext;
import data.database.interfaces.ICategoryContext;
import models.AlbumCategory;
import models.exceptions.AlbumException;

import java.util.Collection;

/**
 * Created by Adriaan on 29-Mar-17.
 */
public class CategoryRepo {

    ICategoryContext context = new MySQLCategoryContext();

    public CategoryRepo(){}

    public CategoryRepo(ICategoryContext context){
        this.context = context;
    }

    public Collection<AlbumCategory> getAllCategories() throws AlbumException{
        return context.getAllCategories();

    }

}
