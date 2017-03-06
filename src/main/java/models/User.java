package models;

/**
 * Created by ruudv on 6-3-2017.
 */
public abstract class User {

    private int ID;
    private String name;

    public User(int id, String name){
        this.ID = id;
        this.name = name;
    }
}
