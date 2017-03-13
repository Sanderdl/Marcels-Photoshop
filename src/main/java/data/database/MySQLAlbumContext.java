package data.database;


import models.GalleryImage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sande on 02/03/2017.
 */
public class MySQLAlbumContext implements IAlbumContext {

    public GalleryImage getImageById(int id) {
        GalleryImage gi = null;

        Connection conn = null;
        PreparedStatement stm = null;

        try{
            conn = MySQLDatabase.dbConnection.getConnection();
            stm = conn.prepareStatement("SELECT * FROM photo WHERE id = ?");
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()){
                String naam = rs.getString("naam");
                byte[] image = rs.getBytes("image");

                gi = new GalleryImage(id, naam, image);
            }

            MySQLDatabase.dbConnection.closeConnection(conn,stm);
        }catch (SQLException ex){
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return gi;
    }

    public HashSet<Integer> allImages(){
        HashSet<Integer> list = new HashSet<>();

        Connection conn = null;
        PreparedStatement stm = null;

        try{
            conn = MySQLDatabase.dbConnection.getConnection();
            stm = conn.prepareStatement("SELECT id FROM photo");

            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");

                list.add(id);
            }
            MySQLDatabase.dbConnection.closeConnection(conn,stm);
        }catch (SQLException ex){
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return list;
    }
}
