package data.database.interfaces;

import models.exceptions.LoginException;

/**
 * Created by Adriaan on 08-Mar-17.
 */
public interface ILoginContext {
    void UserLogin(String username, String password) throws LoginException;
}
