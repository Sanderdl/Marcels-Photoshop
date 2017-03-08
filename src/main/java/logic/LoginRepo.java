package logic;

import data.database.MySQLLoginContext;
import data.database.interfaces.ILoginContext;

/**
 * Created by Adriaan on 08-Mar-17.
 */
public class LoginRepo {
    private ILoginContext context;

    public LoginRepo(){
        this.context = new MySQLLoginContext();
    }

    public void PhotographerLogin(){

    }

    public void UserLogin(){

    }

    public void AdminLogin(){

    }

}
