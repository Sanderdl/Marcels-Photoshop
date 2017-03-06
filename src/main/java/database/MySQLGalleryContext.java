package database;


import models.GalleryImage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sande on 02/03/2017.
 */
public class MySQLGalleryContext implements IGalleryContext {
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public MySQLGalleryContext() {
        innitConnection();
    }

    private void openConnection(){
        rs = null;
        stm = null;
        con = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://web401.your-webhost.nl:3306/qb401179_Teken" ,
            "qb401179_sander","1234");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    private void innitConnection() {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        openConnection();
    }

    private void closeConnection(){
        try {
            rs.close();
            stm.close();
            con.close();
        }catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    public GalleryImage getImageById(int id) {
        GalleryImage gi = null;

        openConnection();

        try{
            stm = con.prepareStatement("SELECT * FROM photo WHERE id = ?");
            stm.setInt(1, id);

            rs = stm.executeQuery();

            while (rs.next()){
                String naam = rs.getString("naam");
                byte[] image = rs.getBytes("image");

                gi = new GalleryImage(id, naam, image);
            }
        }catch (Exception ex){

        }finally {
            closeConnection();
        }
        return gi;
    }

    public List<Integer> allImages(){
        List<Integer> list = new ArrayList<Integer>();

        openConnection();

        try{
            stm = con.prepareStatement("SELECT id FROM photo");

            rs = stm.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");

                list.add(id);
            }
        }catch (Exception ex){

        }finally {
            closeConnection();
        }
        return list;
    }
}
