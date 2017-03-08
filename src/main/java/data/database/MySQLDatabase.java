package data.database;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Adriaan on 06-Mar-17.
 */
public class MySQLDatabase {

    private final static String connectionString = "jdbc:mysql://web401.your-webhost.nl:3306/qb401179_Teken";
    private final static String user = "qb401179_sander";
    private final static String password = "1234";
    public final static MySQLDatabase dbConnection = new MySQLDatabase();

    public MySQLDatabase(){
        if(dbConnection == null){
            initConnection();
        }
    }

    public Connection getConnection() throws SQLException {

        try {
            Connection conn = DriverManager.getConnection(connectionString, user, password);
            System.out.println("Connected to MySql: " + conn.toString());
            return conn;

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
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
