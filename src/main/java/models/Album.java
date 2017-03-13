package models;

/**
 * Created by lucreinink on 13/03/2017.
 */
public class Album {

    private String name;

    private Photographer owner;

    private int id;

    public Album(String name, Photographer owner, int id) {
        this.name = name;
        this.owner = owner;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Photographer getOwner() {
        return owner;
    }

    public void setOwner(Photographer owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
