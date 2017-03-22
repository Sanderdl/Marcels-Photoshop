package data.database.interfaces;

import models.Registration;

import java.sql.SQLException;

/**
 * Created by lucreinink on 06/03/2017.
 */
public interface IRegistrationContext {

    void registerUser(Registration registration, String verified) throws SQLException;

}
