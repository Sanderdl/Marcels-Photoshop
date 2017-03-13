package data.database;

import data.database.interfaces.IExtrasContext;

import java.sql.*;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sande on 13/03/2017.
 */
public class MySQLExtrasContext implements IExtrasContext{

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    private static final Logger LOGGER = Logger.getLogger(MySQLExtrasContext.class.getName());

    @Override
    public HashSet<String> getAvailableExtras() {
        HashSet<String> extras = new HashSet<>();

        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT * FROM  Extras");

            rs = stm.executeQuery();

            while(rs.next()){
                extras.add(rs.getString("extraName"));
            }
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }catch (SQLException ex){
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return extras;
    }
}
