package models;

/**
 * Created by ruudv on 6-3-2017.
 */
public abstract class User {

    private int id;
    private String name;

    public User(int id, String name){
        this.id = id;
        this.name = name;
    }
}
