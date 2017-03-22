package data.database;

import data.database.interfaces.IRegistrationContext;
import logic.Hash;
import models.Registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucreinink on 06/03/2017.
 */
public class MySQLRegistrationContext implements IRegistrationContext {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public void registerUser(Registration registration, String verified) throws SQLException {
        try{
            String passHash = Hash.passHash(registration.getPassword());
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("INSERT INTO Account(username, password, name, email, status, role) " +
                    "values(?, ?, ?, ?, ?, ?)");
            stm.setString(1, registration.getUserName());
            stm.setString(2, passHash);
            stm.setString(3, registration.getName());
            stm.setString(4, registration.getEmail());
            stm.setString(5, verified);
            stm.setString(6, registration.getRole());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }

}
