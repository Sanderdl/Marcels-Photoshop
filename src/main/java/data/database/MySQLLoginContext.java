package data.database;

import data.database.interfaces.ILoginContext;
import logic.Hash;
import models.User;
import models.exceptions.LoginException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Adriaan on 08-Mar-17.
 */
public class MySQLLoginContext implements ILoginContext {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;
    private static final Logger LOGGER = Logger.getLogger(MySQLLoginContext.class.getName());

    public User userLogin(String username, String password) throws LoginException, SQLException {
        try {
            // Call encrypting method
            // password = EncryptStuffPlz(password);

            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT Password FROM Account WHERE Username = ?");
            stm.setString(1, username);

            rs = stm.executeQuery();
            if (rs.next()) {
                String hashedPass = rs.getString("Password");
                if (!Hash.validatePass(password, hashedPass))
                    throw new LoginException("Username and password don't match");
            }else {
                throw new LoginException("Username and password don't match");
            }

            stm = con.prepareStatement("SELECT * FROM Account WHERE Username = ?");
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                User foundUser = null;
                if (rs.getString("Status").equals(User.UserStatus.verified.toString())) {

                    User.UserRoles role = User.UserRoles.valueOf(rs.getString("Role"));
                    if (role == User.UserRoles.Customer || role == User.UserRoles.Photographer || role == User.UserRoles.Admin) {
                        // Is a customer
                        foundUser = User.generateUser(rs, role);
                    }
                    //else if (role == User.UserRoles.Admin) {
                    //    foundUser = generateAdmin(rs);
                    //}
                }
                rs.close();
                MySQLDatabase.dbConnection.closeConnection(con, stm);
                // No user found
                if (foundUser == null)
                    throw new LoginException("No verified or unblocked user found with these credentials");
                return foundUser;
            } else {
                throw new LoginException("No verified or unblocked user found with these credentials");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new LoginException("Error while connecting to the database");
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }

//    private void generatePhotographer(ResultSet rs, String role) throws SQLException{
//        int id = -1;
//        String uName = "";
//        String name = "";
//        String eMail = "";
//        while(rs.next()){
//            // id, uName, pass, name, email, status, role
//            // currently overly verbose to keep method straightforward
//            id = rs.getInt("AccountID");
//            uName = rs.getString("Username");
//            name = rs.getString("Name");
//            eMail = rs.getString("Email");
//        }
//        Photographer p = new Photographer(1, uName, name, eMail);
//    }


    private User generateAdmin(ResultSet rs) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
