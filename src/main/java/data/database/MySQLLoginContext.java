package data.database;

import data.database.interfaces.ILoginContext;
import models.Customer;
import models.exceptions.LoginException;

import java.io.IOException;
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

    public void UserLogin(String username, String password) throws LoginException{
        try{
            // Call encrypting method
            // password = EncryptStuffPlz(password);

            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT * FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?");
            stm.setString(1, username);
            stm.setString(2, password);

            rs = stm.executeQuery();
            if(rs.isBeforeFirst()){
                if(rs.getString("ROLE") == "Client"){
                    // Is a client
                    generateClient(rs);
                }
                else if(rs.getString("ROLE") == "Photographer"){
                    generatePhotographer(rs);
                }
                else if(rs.getString("ROLE") == "Admin"){
                    generateAdmin(rs);
                }
                rs.close();
                MySQLDatabase.dbConnection.closeConnection(con, stm);
                // No user found
                throw new LoginException("No user found with these credentials");
            }
        }
        catch( SQLException ex ){
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new LoginException("Error while connecting to the database");

        }
    }

    private void generateClient(ResultSet rs) throws SQLException{

        while(rs.next()){
            // id, uName, pass, name, email, status, role

        }
        //Customer c = new Customer(1,"name", "", "")

    }

    private void generatePhotographer(ResultSet rs){

    }

    private void generateAdmin(ResultSet rs){
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
