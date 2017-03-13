package data.database.interfaces;

import models.User;
import models.exceptions.LoginException;

/**
 * Created by Adriaan on 08-Mar-17.
 */
public interface ILoginContext {
    User userLogin(String username, String password) throws LoginException;
}
