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

    public Collection<AlbumCategory> getCategoryForAlbum(int albumId) throws AlbumException{
        if(isPositive(albumId)){
            return context.getCategoryForAlbum(albumId);
        }
        throw new AlbumException("ERROR: The requested album does not exist.");

    }

    public void addCategoryToAlbum(Collection<AlbumCategory> catList, int albumId) throws AlbumException{
        // retrieve existing categories
        if(isPositive(albumId)){
            Collection<AlbumCategory> acList = context.getCategoryForAlbum(albumId);
            filterDuplicates(acList, catList);
            if(catList.size() > 0){
                context.addCategoryToAlbum(catList, albumId);
                return;
            }
            throw new AlbumException("ERROR: The desired categories cannot be added due to duplicate values.");
        }
        throw new AlbumException("ERROR: The requested album does not exist.");
    }

    private boolean isPositive(int Id){
        if(Id >= 0){
            return true;
        }
        return false;
    }

    private void filterDuplicates(Collection<AlbumCategory> existingList, Collection<AlbumCategory> catList){
        for (AlbumCategory ac : existingList)
        {
            if(catList.contains(ac)){
                catList.remove(ac);
            }
        }
    }

}