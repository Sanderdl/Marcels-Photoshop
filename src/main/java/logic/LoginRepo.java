package logic;

import data.database.MySQLLoginContext;
import data.database.interfaces.ILoginContext;
import models.exceptions.LoginException;

/**
 * Created by Adriaan on 08-Mar-17.
 */
public class LoginRepo {
    private ILoginContext context;

    public LoginRepo(){
        this.context = new MySQLLoginContext();
    }

    public void UserLogin(String loginName, String password) throws LoginException{
        try{
            context.UserLogin(loginName, password);
        }
        catch(LoginException ex){
            throw ex;
        }

    }

}
