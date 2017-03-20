package logic;

import data.database.MySQLProductContext;
import data.database.interfaces.IProductContext;
import models.ProductRegistration;
import models.User;
import models.exceptions.UploadException;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Created by Tomt on 13-3-2017.
 */
public class UploadRepo {
    private IProductContext context = new MySQLProductContext();

    private boolean validateTitle(final String hex) throws UploadException {
        boolean success = (hex.length() > 1 && hex.length() <= 50);

        if (success) {
            return true;
        }
        throw new UploadException("Invalid name length");
    }

    private boolean validatePrice(final double hex) throws UploadException {
        boolean success = (hex > 1 && hex < 1000);

        if (success) {
            return true;
        }
        throw new UploadException("Invalid price");
    }

    // picture check
    private boolean validateBlob(final Blob hex) throws UploadException, SQLException {
        boolean success = (hex.length() > 0);

        if (success) {
            return true;
        }
        throw new UploadException("Invalid photo");
    }

    public void validateUpload(ProductRegistration productRegistration, User u) throws UploadException, SQLException {
        validateTitle(productRegistration.getTitle());
        validatePrice(productRegistration.getPrice());
        java.util.Date date = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        productRegistration.setDate(sqlDate);
        try {
            this.context.uploadPhoto(u.getId(), productRegistration.getTitle(), productRegistration.getAlbum(), productRegistration.getPicture().getBytes(), productRegistration.getPrice(), true, productRegistration.getDate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
