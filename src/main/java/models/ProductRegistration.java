package models;

import java.sql.Date;

/**
 * Created by ruudv on 13-3-2017.
 */
public class ProductRegistration {

    private String title;
    private Double price;
    private Byte[] picture;
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

    public Byte[] getPicture() {
        return picture;
    }

    public void setPicture(Byte[] picture) {
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
