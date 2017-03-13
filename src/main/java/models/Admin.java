package models;

/**
 * Created by Riccardo on 13/3/2017.
 */
public class Admin extends User {

    public Admin(int ID, String userName, String name, String email, User.UserStatus status){
        super(ID, userName, name, email, status);
    }
}
