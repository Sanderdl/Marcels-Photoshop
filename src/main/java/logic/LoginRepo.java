package logic;

import data.database.MySQLLoginContext;
import data.database.interfaces.ILoginContext;
import models.User;
import models.exceptions.LoginException;

import java.sql.SQLException;

/**
 * Created by Adriaan on 08-Mar-17.
 */
public class LoginRepo {
    private ILoginContext context;

    public LoginRepo() {
        this.context = new MySQLLoginContext();
    }

    // For testing.....maybe.
    public LoginRepo(ILoginContext context) {
        this.context = context;
    }


    public String getUserLanguage(User user) throws SQLException {
        return context.getUserLanguage(user);
    }

    public void setUserLanguage(String language, User user) throws SQLException, LoginException {
        if ("en".equals(language) || "nl".equals(language))
            context.setUserLanguage(language, user);
        else
            throw new LoginException(language +" is not an valid language");
    }

    public User UserLogin(String loginName, String password) throws LoginException, SQLException {
        try {
            verifyPassword(password);
            verifyLoginName(loginName);
            return context.userLogin(loginName, password);

        } catch (LoginException ex) {
            throw ex;
        }
    }


    //<editor-fold desc="Login sanity check">
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
    //</editor-fold>

}
