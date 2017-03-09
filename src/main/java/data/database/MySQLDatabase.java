package data.database;

import org.springframework.jndi.JndiTemplate;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Adriaan on 06-Mar-17.
 */
public class MySQLDatabase {

    private DataSource dataSource;

    public final static MySQLDatabase dbConnection = new MySQLDatabase();

    public MySQLDatabase(){
        if(dbConnection == null){
            initConnection();
        }
    }

    public Connection getConnection() throws SQLException {

        try {
            return dataSource.getConnection();

        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            throw ex;
        }
    }

    public void closeConnection(Connection con) {
        try {
            con.close();
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    public void closeConnection(Connection con, PreparedStatement stm, ResultSet rs){
        try {
            rs.close();
            stm.close();
            con.close();
        }
        catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    private void initConnection() {
        try {
            JndiTemplate jndiTemplate = new JndiTemplate();
            dataSource
                    = (DataSource) jndiTemplate.lookup("java:comp/env/jdbc/photoDB");
        }catch (NamingException ex){
            System.out.println("database not found");
        }
    }

}
