package data.database;

import java.sql.SQLException;

/**
 * Created by lucreinink on 06/03/2017.
 */
public interface IRegistrationContext {

    void registerUser(String userName, String password, String name, String email, String status, String role) throws SQLException;

}
