package data.database;

import data.database.interfaces.IExtrasContext;
import models.Extra;
import models.exceptions.ExtraException;
import models.exceptions.UploadException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by sande on 13/03/2017.
 */
public class MySQLExtrasContext implements IExtrasContext {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    private static final Logger LOGGER = Logger.getLogger(MySQLExtrasContext.class.getName());

    @Override
    public List<Extra> getAvailableExtras() throws UploadException {

        List<Extra> extras = new LinkedList<>();

        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("SELECT * FROM  Extras");

            rs = stm.executeQuery();

            while (rs.next()) {
                extras.add(new Extra(rs.getInt("ExtraID"), rs.getString("ExtraName"),
                        rs.getDouble("ExtraPrice"), rs.getBoolean("Available")));
            }
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            throw new UploadException("Could not get available extras");
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }

        return extras;
    }

    @Override
    public void registerExtras(int imageID, int[] extras) throws SQLException {

        try{
            con = MySQLDatabase.dbConnection.getConnection();
            for (int extraId : extras) {
                stm = con.prepareStatement("INSERT INTO ExtraSet(FotoID, ExtraID) values(?, ?)");
                stm.setInt(1, imageID);
                stm.setInt(2, extraId);
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }

    public void addNewExtraProduct(String name, double price, boolean available) throws UploadException
    {
        try{
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("INSERT INTO Extras(ExtraName, ExtraPrice, Available) values(?, ?, ?)");
            stm.setString(1, name);
            stm.setDouble(2, price);
            stm.setInt(3, available? 1 : 0);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw  new UploadException("Extra wasn't added properly");
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }

    }

    public void changeAvailable(String name, boolean available) throws UploadException
    {
        try{
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("UPDATE Extras SET Available = ? WHERE ExtraName = ?;");
            stm.setInt(1, available? 1 : 0);
            stm.setString(2, name);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw  new UploadException("Extra wasn't added properly");
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }

    public void changePrice(String name, double price) throws UploadException
    {
        try{
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("UPDATE Extras SET price = ? WHERE ExtraName = ?;");
            stm.setDouble(1, price);
            stm.setString(2, name);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw  new UploadException("Extra wasn't added properly");
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }

    public void deleteExtra(int id) throws ExtraException {
        try {
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("DELETE FROM Extras WHERE ExtraID = ?");
            stm.setInt(1, id);
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLExtrasContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new ExtraException("Extra wasn't deleted properly");
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }
}
