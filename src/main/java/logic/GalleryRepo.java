package logic;

import data.database.MySQLGalleryContext;
import data.database.interfaces.IGalleryContext;
import models.GalleryImage;
import models.exceptions.GalleryException;
import models.exceptions.PageOutOfBoundsException;

import java.util.Map;

/**
 * Created by Adriaan on 22-Mar-17.
 */
public class GalleryRepo {

    private IGalleryContext context;

    public GalleryRepo(){
        this.context = new MySQLGalleryContext();
    }

    public Map<Integer, GalleryImage> allImages( int pageNumber) throws GalleryException, PageOutOfBoundsException{
        if(pageNumber <= 0){
          throw new PageOutOfBoundsException("Always be positive about your numbers") ;
        }
        pageNumber = (pageNumber - 1) * 24;

        return context.allImages(pageNumber);
    }





}
