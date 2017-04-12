package models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ruudv on 6-3-2017.
 */
public abstract class User implements Serializable {

    private int id;
    private String userName;
    private String name;
    private String email;
    private UserStatus status;

    public enum UserStatus {
        verified, not_verified, blocked, deleted, ERROR
    }


    public User(int id, String userName, String name, String email, UserStatus status) {
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

    public static User generateUser(ResultSet rs, User.UserRoles role) throws SQLException {
        // NOTE: there is currently no difference between customers and photographers in this phase
        // if a more specific implementation is required, all generate methods must be refactored

        // table columns: id, uName, pass, name, email, status, role
        // currently overly verbose to keep method straightforward
        int id = rs.getInt("AccountID");
        String uName = rs.getString("Username");
        String name = rs.getString("Name");
        String eMail = rs.getString("Email");
        User.UserStatus status = User.UserStatus.valueOf(rs.getString("Status"));
        switch (role) {
            case Customer:
                Customer c = new Customer(id, uName, name, eMail, status);
                return c;
            case Photographer:
                Photographer p = new Photographer(id, uName, name, eMail, status);
                return p;
            case Admin:
                Admin a = new Admin(id, uName, name, eMail, status);
                return a;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getClass().getName().toString();
    }
}
