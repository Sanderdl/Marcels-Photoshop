package models;

/**
 * Created by ruudv on 6-3-2017.
 */
public abstract class User {

    private int id;
    private String userName;
    private String name;
    private String email;

    private enum UserTypes {
        CUSTOMER, PHOTOGRAPHER;
    }

    public User(int id, String userName, String name, String email){
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.email = email;
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

    public static User createNewUser(int id, String userName, String name, String email, String role) {
        User user = null;
        if (role.toUpperCase().equals(UserTypes.CUSTOMER.toString())) {
            user = new Customer(id, userName, name, email);
        } else if (role.toUpperCase().equals(UserTypes.PHOTOGRAPHER.toString())) {
            user = new Photographer(id, userName, name, email);
        }

        return user;
    }
}
