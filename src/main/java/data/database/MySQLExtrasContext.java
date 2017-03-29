package data.database;

import data.database.interfaces.IExtrasContext;
import models.Extra;
import models.exceptions.UploadException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
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
                        rs.getDouble("ExtraPrice")));
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

    private void AddNewExtraProduct(String name, int price, boolean available) throws UploadException
    {
        try{
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("INSERT INTO Extras(ExtraName, ExtraPrice, Available) values(?, ?, ?)");
            stm.setString(1, name);
            //stm.setInt(2, extraId);
            //stm.setInt(2, extraId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }

    }


}
