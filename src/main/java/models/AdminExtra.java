package models;

/**
 * Created by sande on 03/04/2017.
 */
public class AdminExtra {
    private int[] extras;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    private String extraName;


    public String getExtraName() {
        return extraName;
    }

    public void setExtraName(String extraName) {
        this.extraName = extraName;
    }

    public AdminExtra() {}

    public int[] getExtras() {
        return extras;
    }

    public void setExtras(int[] extras) {
        this.extras = extras;
    }
}
