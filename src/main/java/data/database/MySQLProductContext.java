package data.database;

import data.database.interfaces.IProductContext;
import models.User;
import models.exceptions.UploadException;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tomt on 13-3-2017.
 */

public class MySQLProductContext implements IProductContext {
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    @Override
    public int uploadPhoto(int ownerId, String name, byte[] photoBytes, double price,
                           boolean isPublic, Date uploadDate)
            throws UploadException {
        try {
            Blob blob = new javax.sql.rowset.serial.SerialBlob(photoBytes);
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("INSERT INTO Foto (OwnerID, Name, FotoBlob, Price, IsPublic, " +
                    "UploadDate) values(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, ownerId);
            stm.setString(2, name);
            stm.setBlob(4, blob);
            stm.setDouble(5, price);
            stm.setInt(6, isPublic ? 1 : 0);
            stm.setDate(7, uploadDate);
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new UploadException("An error occured while connecting to the database.");
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
        return -1;
    }

    @Override
    public int uploadPhotoWithAlbum(int ownerId, String name, int albumid, byte[] photoBytes, double price, boolean isPublic, Date uploadDate) throws UploadException {
        try {
            Blob blob = new javax.sql.rowset.serial.SerialBlob(photoBytes);
            con = MySQLDatabase.dbConnection.getConnection();
            stm = con.prepareStatement("INSERT INTO Foto (OwnerID, Name, AlbumID, FotoBlob, Price, IsPublic, " +
                    "UploadDate) values(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, ownerId);
            stm.setString(2, name);
            stm.setInt(3, albumid);
            stm.setBlob(4, blob);
            stm.setDouble(5, price);
            stm.setInt(6, isPublic ? 1 : 0);
            stm.setDate(7, uploadDate);
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new UploadException("An error occurred while connecting to the database.");
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
        return -1;
    }

    @Override
    public Collection<User> getAllUsers() {

        Collection<User> users = new HashSet<>();
        try {
            this.con = MySQLDatabase.dbConnection.getConnection();
            this.stm = this.con.prepareStatement("SELECT * FROM Account WHERE NOT Role LIKE(\"Admin\")");

            this.rs = this.stm.executeQuery();


            while (this.rs.next()) {
                User.UserRoles role = User.UserRoles.valueOf(rs.getString("Role"));
                users.add(User.generateUser(this.rs, role));
            }

        } catch (SQLException e) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return users;
    }

    @Override
    public void addSharedWith(int photoId, int[] userIds) {

        try {
            con = MySQLDatabase.dbConnection.getConnection();
            for (int i : userIds) {
                stm = con.prepareStatement("INSERT INTO Visibility (AccountID, FotoID) VALUES (?, ?)");
                stm.setInt(1, i);
                stm.setInt(2, photoId);
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLAlbumContext.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            MySQLDatabase.dbConnection.closeConnection(con, stm);
        }
    }


}
