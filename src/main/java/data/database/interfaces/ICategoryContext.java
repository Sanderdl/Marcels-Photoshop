package data.database.interfaces;

import models.AlbumCategory;
import models.exceptions.AlbumException;

import java.util.Collection;
import java.util.List;

/**
 * Created by Adriaan on 29-Mar-17.
 */
public interface ICategoryContext {

    Collection<AlbumCategory> getAllCategories() throws AlbumException;
}
