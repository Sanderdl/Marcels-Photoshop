package models;

/**
 * Created by Adriaan on 29-Mar-17.
 */
public class AlbumCategory {
    private String categoryName;
    private int categoryId;

    public AlbumCategory(int categoryId, String categoryName){
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }
}
