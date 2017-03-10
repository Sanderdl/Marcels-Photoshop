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
public class MySQLGalleryContext implements IGalleryContext {

    public MySQLGalleryContext() {
    }

//    private void openConnection(){
//        rs = null;
//        stm = null;
//        con = null;
//
//        try {
//            con = DriverManager.getConnection("jdbc:mysql://web401.your-webhost.nl:3306/qb401179_Teken" ,
//            "qb401179_sander","1234");
//
//        } catch (SQLException ex) {
//            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }
//    }

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

            stm.close();
            stm = null;

            conn.close();
            conn = null;
        }catch (Exception ex){
            Logger.getLogger(MySQLGalleryContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }

                stm = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }

                conn = null;
            }
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
            stm.close();
            stm = null;

            conn.close();
            conn = null;
        }catch (Exception ex){

        }finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }

                stm = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlex) {
                    // ignore, as we can't do anything about it here
                }

                conn = null;
            }
        }
        return list;
    }
}
