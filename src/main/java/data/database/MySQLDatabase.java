package data.database;

import org.springframework.jndi.JndiTemplate;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Adriaan on 06-Mar-17.
 */
public class MySQLDatabase {

    private DataSource dataSource;

    public final static MySQLDatabase dbConnection = new MySQLDatabase();

    private MySQLDatabase() {
        initConnection();
    }

    public Connection getConnection() throws SQLException {

        try {
            return dataSource.getConnection();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            throw ex;
        }
    }

    public void closeConnection(Connection conn, Statement stm) {
        try {
            stm.close();
            stm = null;

            conn.close();
            conn = null;

        } catch (SQLException ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {

            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }

                stm = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MySQLDatabase.class.getName()).log(Level.INFO, ex.getMessage(), ex);
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
        } catch (NamingException ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

}
