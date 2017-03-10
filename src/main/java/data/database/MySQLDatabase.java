package data.database;

import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void closeConnection(Connection conn, Statement stm) throws SQLException{
        try {
            stm.close();
            stm = null;

            conn.close();
            conn = null;
        }finally {

            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }

                stm = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }

                conn = null;
            }
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
