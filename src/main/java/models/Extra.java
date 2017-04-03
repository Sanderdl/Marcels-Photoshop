package models;

/**
 * Created by lucreinink on 15/03/2017.
 */
public class Extra {

    private int id;

    private String name;

    private double price;

    private boolean available;

    public Extra(int id, String name, double price, boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getAvailable(){ return available; }

    public void setAvailable(boolean available) { this.available =available; }

    @Override
    public String toString() {
        return this.name;
    }
}
