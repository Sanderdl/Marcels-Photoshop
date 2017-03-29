package data.database;

import data.database.interfaces.ICategoryContext;
import models.AlbumCategory;
import models.exceptions.AlbumException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Adriaan on 29-Mar-17.
 */
public class MySQLCategoryContext implements ICategoryContext {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    @Override
    public Collection<AlbumCategory> getAllCategories() throws AlbumException{
        List<AlbumCategory> catList = new ArrayList<AlbumCategory>();
        try{
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT * FROM Categories");

            rs = stm.executeQuery();

            while(rs.next()){
                catList.add(new AlbumCategory(rs.getInt("CategoryID"), rs.getString("CategoryName")));

            }
            return catList;

        }
        catch(SQLException ex){
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new AlbumException("An error occurred while connecting with the database.");
        }

    }

}
