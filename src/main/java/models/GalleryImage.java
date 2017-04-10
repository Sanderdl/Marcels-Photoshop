package models;

/**
 * Created by sande on 02/03/2017.
 */
public class GalleryImage {
    private int id;
    private String name;
    private byte[] image;
    private double price;
    private boolean isAlbum;
    private boolean isPublic;

    public GalleryImage(int id, String name, byte[] image, double price, boolean isPublic){
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.isAlbum = false;
        this.isPublic = isPublic;
    }

    public GalleryImage(int id, String name, byte[] image, double price){
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.isAlbum = false;
    }

    public GalleryImage(int id, String name, byte[] image, boolean isAlbum){
        this.id = id;
        this.name = name;
        this.image = image;
        this.isAlbum = isAlbum;
    }

    public GalleryImage(int id, String name, byte[] image){
        this.id = id;
        this.name = name;
        this.image = image;
        this.isAlbum = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() { return image; }

    public double getPrice() {
        return price;
    }

    public boolean isAlbum() {
        return isAlbum;
    }

    public boolean isPublic() {
        return isPublic;
    }
}
