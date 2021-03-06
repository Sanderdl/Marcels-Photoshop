package models;

/**
 * Created by sande on 08/03/2017.
 */
public class Registration {

    private String userName;
    private String password;
    private String name;
    private String email;
    private String role;

    public Registration(String userName, String password, String name, String email, String role) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Registration() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
