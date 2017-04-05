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
        finally{
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }

    }

    @Override
    public Collection<AlbumCategory> getCategoryForAlbum(int albumId) throws AlbumException {
        List<AlbumCategory> catList = new ArrayList<AlbumCategory>();
        try{
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement(
                    "SELECT C.CategoryID, C.CategoryName FROM AlbumCategories AC JOIN Categories C ON AC.CategoryID = C.CategoryID WHERE AlbumID = ?");
            stm.setInt(1, albumId);

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
        finally{
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }

    @Override
    public void addCategoryToAlbum(Collection<AlbumCategory> albumList, int albumId) throws AlbumException {
        try{
            con = MySQLDatabase.dbConnection.getConnection();
            for (AlbumCategory ac : albumList)
            {
                stm = con.prepareStatement("INSERT INTO AlbumCategories (AlbumID, CategoryID) VALUES (?, ?)");
                stm.setInt(1, albumId);
                stm.setInt(2, ac.getCategoryId());
                stm.executeUpdate();
            }
        }
        catch (SQLException ex){
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new AlbumException("An error occurred while connecting with the database or setting categories.");
        }
        finally{
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }

    @Override
    public void deleteCategoryFromAlbum(int albumId) throws AlbumException {
        try
        {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("DELETE FROM AlbumCategories WHERE AlbumID= ?");
            stm.setInt(1, albumId);
            stm.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new AlbumException("An error occurred while connecting with the database or setting categories.");
        }
        finally
        {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }


}
