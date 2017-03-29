package data.database.interfaces;

import models.AlbumCategory;

import java.util.List;

/**
 * Created by Adriaan on 29-Mar-17.
 */
public interface ICategoryContext {

    List<AlbumCategory> getAllCategories();
}
