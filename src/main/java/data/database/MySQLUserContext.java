package data.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lucreinink on 06/03/2017.
 */
public class MySQLUserContext implements IUserContext {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public void registerUser(String userName, String password, String name, String email, String status, String role) throws SQLException {

        try{
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("INSERT INTO ACCOUNT(?, ?, ?, ?, ?, ?)");
            stm.setString(1, userName);
            stm.setString(2, password);
            stm.setString(3, name);
            stm.setString(4, email);
            stm.setString(5, status);
            stm.setString(6, role);
            stm.executeUpdate();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MySQLGalleryContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm, rs);
        }
    }

}