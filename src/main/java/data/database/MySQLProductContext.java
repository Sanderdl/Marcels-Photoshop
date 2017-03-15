package data.database;

import data.database.interfaces.IProductContext;
import models.Extra;
import models.GalleryImage;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tomt on 13-3-2017.
 */

public class MySQLProductContext implements IProductContext {
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    @Override
    public void uploadPhoto(int ownerid, String name, int albumid, byte[] photoBytes, double price, boolean ispublic, Date uploaddate) throws SQLException {

        Blob photoBlob = con.createBlob();
        photoBlob.setBytes(1, photoBytes);
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("INSERT INTO FOTO (?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, ownerid);
            stm.setString(2, name);
            stm.setInt(3, albumid);
            stm.setBlob(4, photoBlob);
            stm.setDouble(5, price);
            stm.setInt(6, (ispublic) ? 1 : 0);
            stm.setDate(7,uploaddate);
            stm.executeUpdate();
        } catch ( SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }

    @Override
    public void registerExtras(GalleryImage image, Extra extra) throws SQLException {

        try {
            this.con = MySQLDatabase.dbConnection.getConnection();
            this.stm = con.prepareStatement("INSERT INTO ExtraSet (?, ?)");
            stm.setInt(1, image.getId());
            stm.setInt(2, extra.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }
}
