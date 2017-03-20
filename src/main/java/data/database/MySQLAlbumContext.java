package data.database;


import data.database.interfaces.IAlbumContext;
import models.Album;
import models.GalleryImage;
import models.Photographer;
import models.exceptions.GalleryException;

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

    public GalleryImage getImageById(int id) throws SQLException {
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

    public Map<Integer, GalleryImage> allImages() throws GalleryException {
        Map<Integer, GalleryImage> list = new HashMap<>();

        try
        {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT * FROM Foto WHERE IsPublic = 1 ORDER BY 'UploadDate' LIMIT 24");

            ResultSet rs = stm.executeQuery();
            while (rs.next())
            {
                Blob b = rs.getBlob("FotoBlob");
                byte[] bytes = b.getBytes(1L, (int) b.length());

                list.put(rs.getInt("FotoID"),
                        new GalleryImage(rs.getInt("FotoID"), rs.getString("Name"), bytes));
            }
        }
        catch (SQLException | NullPointerException ex)
        {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new GalleryException(ex.getMessage());
        }
        finally
        {
            try{
                MySQLDatabase.dbConnection.closeConnection(con, stm);
            }
            catch(SQLException ex){
                throw new GalleryException(ex.getMessage());
            }

        }
        return list;
    }

    @Override
    public Collection<Album> getAllAlbumsByUser(Photographer owner) throws SQLException {

        Collection<Album> albums = new ArrayList<>();
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT * FROM Album where AccountID = ?");
            stm.setInt(1, owner.getId());

            rs = stm.executeQuery();

            if (rs.next()) {
                albums.add(new Album(rs.getString("AlbumName"), owner, rs.getInt("AlbumID")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }

        return albums;
    }

    public void createAlbum(int accountID, String albumName) throws SQLException
    {
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("INSERT INTO Album (AccountID, AlbumName) values(?, ?)");
            stm.setInt(1, accountID);
            stm.setString(2, albumName);
            stm.executeUpdate();
        } catch ( SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }
}
