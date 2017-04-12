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
    private static final String STATUS = "Status";

    public User userLogin(String username, String password) throws LoginException, SQLException {
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT Password FROM Account WHERE Username = ?");
            stm.setString(1, username);

            rs = stm.executeQuery();
            if (rs.next()) {
                String hashedPass = rs.getString("Password");
                if (!Hash.validatePass(password, hashedPass))
                    throw new LoginException("Username and password don't match");
            } else {
                throw new LoginException("Username and password don't match");
            }

            stm = con.prepareStatement("SELECT * FROM Account WHERE Username = ?");
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                User foundUser = null;
                System.out.println(rs.getString(STATUS));
                if (rs.getString(STATUS).equals(User.UserStatus.verified.toString())) {

                    User.UserRoles role = User.UserRoles.valueOf(rs.getString("Role"));
                    if (role == User.UserRoles.Customer || role == User.UserRoles.Photographer || role == User.UserRoles.Admin) {
                        // Is a customer
                        foundUser = User.generateUser(rs, role);
                    }
                } else if (rs.getString(STATUS).equals(User.UserStatus.not_verified.toString())) {
                    foundUser = User.generateUser(rs, User.UserRoles.Customer);
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

    @Override
    public String getUserLanguage(User user) throws SQLException {
        String language = null;
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT Language FROM Account WHERE AccountID = ?");
            stm.setInt(1, user.getId());
            rs = stm.executeQuery();

            if (rs.next()) {
                language = rs.getString("Language");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
        return language;
    }

    @Override
    public void setUserLanguage(String language, User user) throws SQLException {
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("UPDATE Account SET Language = ? WHERE AccountID = ?");
            stm.setString(1, language);
            stm.setInt(2, user.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }
}
