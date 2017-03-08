package data.database;


import models.GalleryImage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sande on 02/03/2017.
 */
public class MySQLGalleryContext implements IGalleryContext {
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

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

        try{
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT * FROM photo WHERE id = ?");
            stm.setInt(1, id);

            rs = stm.executeQuery();

            while (rs.next()){
                String naam = rs.getString("naam");
                byte[] image = rs.getBytes("image");

                gi = new GalleryImage(id, naam, image);
            }
        }catch (Exception ex){
            Logger.getLogger(MySQLGalleryContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm, rs);
        }
        return gi;
    }

    public List<Integer> allImages(){
        List<Integer> list = new ArrayList<Integer>();
        try{
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT id FROM photo");

            rs = stm.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");

                list.add(id);
            }
        }catch (Exception ex){

        }finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm, rs);
        }
        return list;
    }
}
