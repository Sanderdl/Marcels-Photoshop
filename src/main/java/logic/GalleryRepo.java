package logic;

import data.database.MySQLGalleryContext;
import data.database.interfaces.IGalleryContext;

/**
 * Created by Adriaan on 22-Mar-17.
 */
public class GalleryRepo {

    private IGalleryContext context;

    public GalleryRepo(){
        this.context = new MySQLGalleryContext();
    }



}
