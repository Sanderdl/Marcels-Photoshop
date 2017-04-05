package models;

/**
 * Created by sande on 02/03/2017.
 */
public class GalleryImage {
    private int id;
    private String name;
    private byte[] image;
    private double price;

    public GalleryImage(int id, String name, byte[] image, double price){
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public GalleryImage(int id, String name, byte[] image){
        this.id = id;
        this.name = name;
        this.image = image;
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
}
