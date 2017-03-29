package data.database;


import data.database.interfaces.IAlbumContext;
import models.Album;
import models.GalleryImage;
import models.Photographer;
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
        int newAblumID = -1;
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
                newAblumID = rs.getInt(1);
            }
            else
            {
                throw new UploadException("Your album wasn't created correctly");
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new UploadException("Album was not created!");
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }

        for (int i: categoryIDs)
        {
            AddCategoryToAlbum(newAblumID, i);
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
}
