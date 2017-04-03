package models;

/**
 * Created by sande on 02/03/2017.
 */
public class GalleryImage {
    private int id;
    private String name;
    private byte[] image;
    private int photographerId;
    private String photographerName;

    public GalleryImage(int id, String name, byte[] image, int photographerId, String photographerName){
        this.id = id;
        this.name = name;
        this.image = image;
        this.photographerId = photographerId;
        this.photographerName = photographerName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public int getPhotographerId() {
        return photographerId;
    }

    public String getPhotographerName() {
        return photographerName;
    }
}
