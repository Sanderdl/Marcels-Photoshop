package models;

<<<<<<< Updated upstream
import java.sql.Date;
=======
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
>>>>>>> Stashed changes

/**
 * Created by ruudv on 13-3-2017.
 */
public class ProductRegistration {

    private String title;
    private Double price;
<<<<<<< Updated upstream
    private Byte[] picture;
=======
    private MultipartFile picture;
>>>>>>> Stashed changes
    private String[] products;
    private int album;
    private int ispublic;
    private Date date;

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

<<<<<<< Updated upstream
    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
=======
    public MultipartFile getPicture() {return picture;}

    public void setPicture(MultipartFile picture) {
>>>>>>> Stashed changes
        this.picture = picture;
    }

    public String[] getProducts() {
        return products;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    public int getAlbum() {
        return album;
    }

    public void setAlbum(int album) {
        this.album = album;
    }

    public int getIspublic(){return ispublic;}

    public void setIspublic(int ispublic){this.ispublic=ispublic;}

    public Date getDate(){return date;}

    public void setDate(Date date){this.date=date;}
}
