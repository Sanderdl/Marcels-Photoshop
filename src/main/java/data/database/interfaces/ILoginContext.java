package data.database.interfaces;

import models.User;
import models.exceptions.LoginException;

import java.sql.SQLException;

/**
 * Created by Adriaan on 08-Mar-17.
 */
public interface ILoginContext {
    User userLogin(String username, String password) throws LoginException, SQLException;

    String getUserLanguage(User user) throws SQLException;

    void setUserLanguage(String language, User user) throws SQLException;
}
