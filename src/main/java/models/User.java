package models;

import java.io.Serializable;

/**
 * Created by ruudv on 6-3-2017.
 */
public abstract class User implements Serializable {

    private int id;
    private String userName;
    private String name;
    private String email;
    private UserStatus status;

    public enum UserStatus{
        verified, not_verified, blocked, deleted, ERROR
    }



    public User(int id, String userName, String name, String email, UserStatus status){
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public enum UserRoles {
        Customer, Photographer, Admin, ERROR
    }
}
