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

public class    MySQLProductContext implements IProductContext {
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    @Override
    public void uploadPhoto(int ownerId, String name, int albumid, byte[] photoBytes, double price, boolean isPublic, Date uploadDate) throws SQLException {

        Blob blob = new javax.sql.rowset.serial.SerialBlob(photoBytes);
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("INSERT INTO Foto (OwnerID, Name, AlbumID, FotoBlob, Price, IsPublic, UploadDate) values(?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, ownerId);
            stm.setString(2, name);
            stm.setInt(3, albumid);
            stm.setBlob(4, blob);
            stm.setDouble(5, price);
            stm.setInt(6, (isPublic) ? 1 : 0);
            stm.setDate(7,uploadDate);
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