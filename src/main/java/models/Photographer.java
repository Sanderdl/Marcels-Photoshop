package models;

/**
 * Created by ruudv on 6-3-2017.
 */
public class Photographer extends User{

    public Photographer(int ID, String userName, String name, String email, User.UserStatus status){
        super(ID, userName, name, email, status);
    }
}
