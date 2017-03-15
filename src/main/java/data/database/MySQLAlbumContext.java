package data.database;


import data.database.interfaces.IAlbumContext;
import models.Album;
import models.GalleryImage;
import models.Photographer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
            stm = conn.prepareStatement("SELECT * FROM photo WHERE id = ?");
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                String naam = rs.getString("naam");
                byte[] image = rs.getBytes("image");

                gi = new GalleryImage(id, naam, image);
            }

            MySQLDatabase.dbConnection.closeConnection(conn, stm);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
        return gi;
    }

    public HashSet<Integer> allImages() throws SQLException {
        HashSet<Integer> list = new HashSet<>();

        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT id FROM photo");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");

                list.add(id);
            }
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
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
}
