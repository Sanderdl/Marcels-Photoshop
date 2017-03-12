package logic;

import data.database.MySQLLoginContext;
import data.database.interfaces.ILoginContext;
import models.exceptions.LoginException;

/**
 * Created by Adriaan on 08-Mar-17.
 */
public class LoginRepo {
    private ILoginContext context;

    public LoginRepo() {
        this.context = new MySQLLoginContext();
    }

    // For testing.....maybe.
    public LoginRepo(ILoginContext context){this.context = context;};

    public void UserLogin(String loginName, String password) throws LoginException {
        try {
            if (verifyLoginName(loginName) && verifyPassword(password)) {
                context.UserLogin(loginName, password);
            }
        }
        catch (LoginException ex) {
            throw ex;
        }
    }

    private boolean verifyPassword(String password) throws LoginException {
        // Any other constraints for unencrypted passwords should be included here.
        if (password.length() >= 8 && password.length() <= 50) {
            return true;
        }
        throw new LoginException("The length of the entered password must be between 8 and 50 characters");
    }

    private boolean verifyLoginName(String loginName) throws LoginException {
        // Any other constraints for login names should be included here.
        if (loginName.length() >= 3 && loginName.length() <= 50) {
            return true;
        }
        throw new LoginException("The length of the entered username must be between 3 and 50 characters");
    }

}
