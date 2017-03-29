package logic;

import data.database.MySQLCategoryContext;
import data.database.interfaces.ICategoryContext;
import models.AlbumCategory;

import java.util.List;

/**
 * Created by Adriaan on 29-Mar-17.
 */
public class CategoryRepo {

    ICategoryContext context = new MySQLCategoryContext();

    public CategoryRepo(){}

    public CategoryRepo(ICategoryContext context){
        this.context = context;
    }

    public List<AlbumCategory> getAllCategories(){
        return context.getAllCategories();

    }

}
