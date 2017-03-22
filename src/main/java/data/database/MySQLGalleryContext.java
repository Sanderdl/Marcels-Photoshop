package data.database;

import data.database.interfaces.IGalleryContext;
import models.GalleryImage;
import models.exceptions.GalleryException;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Adriaan on 22-Mar-17.
 */
public class MySQLGalleryContext implements IGalleryContext {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public GalleryImage getImageById(int id) throws GalleryException {
        GalleryImage gi = null;

        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = MySQLDatabase.dbConnection.getConnection();
            stm = conn.prepareStatement("SELECT name, FotoBlob FROM Foto WHERE FotoID = ?");
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                String naam = rs.getString("Name");
                byte[] image = rs.getBytes("FotoBlob");

                gi = new GalleryImage(id, naam, image);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
        return gi;
    }

    public Map<Integer, GalleryImage> allImages(int startIndex) throws GalleryException {
        Map<Integer, GalleryImage> list = new HashMap<>();

        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT * FROM Foto WHERE IsPublic = 1 ORDER BY 'UploadDate' LIMIT ?,24");
            stm.setInt(1, startIndex);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Blob b = rs.getBlob("FotoBlob");
                byte[] bytes = b.getBytes(1L, (int) b.length());

                list.put(rs.getInt("FotoID"),
                        new GalleryImage(rs.getInt("FotoID"), rs.getString("Name"), bytes));
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new GalleryException(ex.getMessage());
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
        return list;
    }

    public int getImageCount()
    {
        int num = 0;
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT COUNT(FotoID) AS Num FROM Foto WHERE IsPublic = 1");

            ResultSet rs = stm.executeQuery();
            if (rs.next())
            {
                num = rs.getInt("Num");
            }
        }
        catch (SQLException | NullPointerException ex)
        {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        finally
        {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }

        return num;
    }

}
