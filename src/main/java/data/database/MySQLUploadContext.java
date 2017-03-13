package data.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tomt on 13-3-2017.
 */

public class MySQLUploadContext {
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public void uploadPhoto(int ownerid, String name, int albumid, Blob photoblob, int price, int ispublic, Date uploaddate) throws SQLException {
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("INSERT INTO FOTO (?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, ownerid);
            stm.setString(2, name);
            stm.setInt(3, albumid);
            stm.setBlob(4, photoblob);
            stm.setInt(5, price);
            stm.setInt(6, ispublic);
            stm.setDate(7,uploaddate);
            stm.executeUpdate();
        } catch ( SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }
}
