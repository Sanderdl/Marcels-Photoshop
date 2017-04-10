package data.database;


import data.database.interfaces.IAlbumContext;
import models.Album;
import models.GalleryImage;
import models.Photographer;
import models.User;
import models.exceptions.GalleryException;
import models.exceptions.UploadException;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sande on 02/03/2017.
 */
public class MySQLAlbumContext implements IAlbumContext {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    @Override
    public Collection<Album> getAllAlbumsByUser(Photographer owner) throws UploadException {

        Collection<Album> albums = new ArrayList<>();
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT * FROM Album where AccountID = ?");
            stm.setInt(1, owner.getId());

            rs = stm.executeQuery();

            while (rs.next()) {
                albums.add(new Album(rs.getString("AlbumName"), owner, rs.getInt("AlbumID")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new UploadException("Images not loaded");
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }

        return albums;
    }

    public void createAlbum(int accountID, String albumName, int[] categoryIDs, byte[] mainPhotoBytes) throws UploadException {
        int newAlbumID = -1;
        try {
            Blob blob = new javax.sql.rowset.serial.SerialBlob(mainPhotoBytes);
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("INSERT INTO Album (AccountID, AlbumName, AlbumFoto) values(?, ?, ?)"
                    ,Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, accountID);
            stm.setString(2, albumName);
            stm.setBlob(3, blob);
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                newAlbumID = rs.getInt(1);
            }
            else
            {
                throw new UploadException("An error occurred while creating the album");
            }

        }
        catch (SQLException ex)
        {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new UploadException("An error occurred while connecting to the database.");
        }
        finally
        {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }

        for (int i: categoryIDs)
        {
            AddCategoryToAlbum(newAlbumID, i);
        }

    }

    private void AddCategoryToAlbum(int albumID, int categoryID) throws UploadException
    {
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("INSERT INTO AlbumCategories (AlbumID, CategoryID) values(?, ?)");
            stm.setInt(1, albumID);
            stm.setInt(2, categoryID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new UploadException("Category could not be linked to album.");
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }

    public Album retrieveAlbumByID(int albumID) throws UploadException
    {
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT  * FROM Album Al JOIN Account Ac ON Al.AccountID = Ac.AccountID WHERE AlbumID = ?");
            stm.setInt(1, albumID);
            rs = stm.executeQuery();
            if (rs.next())
            {
                return new Album(rs.getString("AlbumName"),
                        new Photographer(rs.getInt("AccountID"),rs.getString("Username")
                            ,rs.getString("Name"),rs.getString("Email"), User.UserStatus.ERROR),
                        -1);
            }

            throw new UploadException("Album could not be loaded");
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new UploadException("Album could not be loaded");
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }

    public Map<Integer, GalleryImage> retrieveImagesForAlbum(int albumID) throws GalleryException
    {
        Map<Integer, GalleryImage> list = new TreeMap<>();

        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT  * FROM Foto WHERE AlbumID = ?");
            stm.setInt(1, albumID);
            rs = stm.executeQuery();
            while (rs.next()) {
                Blob b = rs.getBlob("FotoBlob");
                byte[] bytes = b.getBytes(1L, (int) b.length());
                list.put(rs.getInt("FotoID"), new GalleryImage(rs.getInt("FotoID"),
                        rs.getString("Name"), bytes, rs.getInt("AlbumID")== 0));

            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new GalleryException(ex.getMessage());
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
        return list;
    }
}
