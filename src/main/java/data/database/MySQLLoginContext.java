package data.database;

import data.database.interfaces.ILoginContext;
import models.Customer;
import models.Photographer;
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
            stm = con.prepareStatement("SELECT * FROM Account WHERE Username = ? AND Password = ?");
            stm.setString(1, username);
            stm.setString(2, password);

            rs = stm.executeQuery();
            if(rs.next()){
                boolean foundUser = false;
                if(rs.getString("Status").equals("verified")){
                    String role = rs.getString("Role");
                    if( role.equals("Customer") ||  role.equals("Photographer")){
                        // Is a client
                        generateClient(rs, role);
                       foundUser = true;
                    }
//                    else if(rs.getString("ROLE") == "Photographer"){
//                        generatePhotographer(rs);
//                    }
                    else if(role.equals("Admin")){
                        generateAdmin(rs);
                        foundUser = true;
                    }
                }
                rs.close();
                MySQLDatabase.dbConnection.closeConnection(con, stm);
                // No user found
                if (!foundUser) throw new LoginException("No verified, unblocked user found with these credentials");
            }
        }
        catch( SQLException ex ){
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new LoginException("Error while connecting to the database");

        }
    }

    private void generateClient(ResultSet rs, String role) throws SQLException{
        // NOTE: there is currently no difference between Clients and photographers in this phase
        // if a more specific implementation is required, all generate methods must be refactored
        int id = -1;
        String uName = "";
        String name = "";
        String eMail = "";
        while(rs.next()){
            // id, uName, pass, name, email, status, role
            // currently overly verbose to keep method straightforward
            id = rs.getInt("AccountID");
            uName = rs.getString("Username");
            name = rs.getString("Name");
            eMail = rs.getString("Email");
        }
        switch (role){
            case "Client":
                Customer c = new Customer(id, uName, name, eMail);
                break;
            case "Photographer":
                Photographer p = new Photographer(1, uName, name, eMail);
                break;
            default:
                break;
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


    private void generateAdmin(ResultSet rs){
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
