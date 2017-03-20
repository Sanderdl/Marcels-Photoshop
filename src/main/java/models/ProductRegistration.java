package models;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.Arrays;

/**
 * Created by ruudv on 13-3-2017.
 */
public class ProductRegistration {

    private String title;
    private Double price;
    private MultipartFile picture;
    private int[] products;
    private int album;
    private boolean isPublic;
    private Date date;

    public ProductRegistration(){}

    public ProductRegistration(String title, Double price, MultipartFile picture, int[] products, int album, boolean isPublic) {

        this.title = title;
        this.price = price;
        this.picture = picture;
        this.products = products;
        this.isPublic = isPublic;
    }

    @Override
    public String toString() {
        return "ProductRegistration{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", products=" + Arrays.toString(products) +
                ", album=" + album +
                ", isPublic=" + isPublic +
                ", date=" + date +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MultipartFile getPicture() {
        return picture;}

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public int[] getProducts() {
        return products;
    }

    public void setProducts(int[] products) {
        this.products = products;
    }

    public int getAlbum() {
        return album;
    }

    public void setAlbum(int album) {
        this.album = album;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean ispublic) {
        this.isPublic = isPublic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
