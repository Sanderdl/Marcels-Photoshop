package data.database;

import data.database.interfaces.IGalleryContext;
import models.GalleryImage;
import models.Photographer;
import models.User;
import models.exceptions.GalleryException;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;
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

        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT * FROM Foto WHERE FotoID = ?");
            stm.setInt(1, id);

            rs = stm.executeQuery();

            if (rs.next()) {
                Blob b = rs.getBlob("FotoBlob");
                byte[] bytes = b.getBytes(1L, (int) b.length());
                gi = new GalleryImage(id, rs.getString("Name"), bytes, rs.getDouble("Price"), rs.getBoolean("IsPublic"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
        return gi;
    }

    public Map<Integer, GalleryImage> allImages(int startIndex) throws GalleryException {
        Map<Integer, GalleryImage> list = new TreeMap<>();

        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("(Select FotoID, AlbumID, Name, FotoBlob From Foto Where AlbumID IS NULL AND IsPublic = 1) Union All (Select AlbumID, AlbumID, AlbumName, AlbumFoto From Album) Order By FotoID Desc Limit ?,24");
            stm.setInt(1, startIndex);
            rs = stm.executeQuery();

            while (rs.next()) {
                Blob b = rs.getBlob("FotoBlob");
                byte[] bytes = b.getBytes(1L, (int) b.length());
                    list.put(rs.getInt("FotoID"), new GalleryImage(rs.getInt("FotoID"),
                            rs.getString("Name"), bytes, rs.getInt("AlbumID")!= 0));

            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new GalleryException(ex.getMessage());
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
        return list;
    }

    public int getImageCount() {
        int num = 0;
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT COUNT(FotoID) AS Num FROM Foto WHERE IsPublic = 1");

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                num = rs.getInt("Num");
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }

        return num;
    }

    public double getHomePageCount() {
        double num = 0;
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("Select Count(FotoID) / 24 AS PageCount from ((Select FotoID, AlbumID From Foto Where AlbumID IS NULL AND IsPublic = 1) Union All (Select AlbumID, AlbumID From Album)) AS FotoAndAlbum");

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                num = rs.getDouble("PageCount");
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }

        return num;
    }

    public Photographer getPhotographerByImageId(int imageId) {

        Photographer photographer = null;

        try {
            this.con = MySQLDatabase.dbConnection.getConnection();
            this.stm = this.con.prepareStatement("SELECT * FROM `Account` a, `Foto` f WHERE FotoID = ? AND f.OwnerID" +
                    " = a.AccountID");

            stm.setInt(1, imageId);

            this.rs = this.stm.executeQuery();
            if (rs.next()) {
                photographer = (Photographer) User.generateUser(this.rs, User.UserRoles.Photographer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return photographer;
    }

    @Override
    public Map<Integer, GalleryImage> allSharedImages(int sharedWithId, int pageNumber) throws GalleryException {

        Map<Integer, GalleryImage> list = new TreeMap<>();

        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT * FROM Visibility v JOIN Foto f ON v.FotoID = f.FotoID WHERE v.AccountID = ? Limit ?,24");
            stm.setInt(1, sharedWithId);
            stm.setInt(2, pageNumber);
            rs = stm.executeQuery();

            while (rs.next()) {
                Blob b = rs.getBlob("FotoBlob");
                byte[] bytes = b.getBytes(1L, (int) b.length());
                list.put(rs.getInt("FotoID"), new GalleryImage(rs.getInt("FotoID"),
                        rs.getString("Name"), bytes, rs.getInt("AlbumID")!= 0));

            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new GalleryException(ex.getMessage());
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
        return list;
    }

    public double getPrivatePageCount(int accountID) {
        double num = 0;
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT COUNT(FotoID)/ 24 AS PageCount FROM Visibility WHERE AccountID = ?");
            stm.setInt(1, accountID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                num = rs.getDouble("PageCount");
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }

        return num;
    }

}
