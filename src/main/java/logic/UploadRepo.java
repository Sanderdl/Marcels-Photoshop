package logic;

import data.database.MySQLProductContext;
import data.database.interfaces.IProductContext;
import models.ProductRegistration;
import models.User;
import models.exceptions.UploadException;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

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

    public void validateUpload(ProductRegistration productregistration, User u) throws UploadException, SQLException {
        validateTitle(productregistration.getTitle());
        validatePrice(productregistration.getPrice());
        try {
            this.context.uploadPhoto(u.getId(), productregistration.getTitle(), productregistration.getAlbum(), productregistration.getPicture().getBytes(), productregistration.getPrice(), true, productregistration.getDate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
