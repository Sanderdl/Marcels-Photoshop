package logic;

import data.database.MySQLProductContext;
import data.database.interfaces.IProductContext;
import models.ProductRegistration;
import models.User;
import models.exceptions.UploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        boolean success = (hex > 0 && hex < 1000);

        if (success) {
            return true;
        }
        throw new UploadException("Price must be between €0.00 and €1000.00");
    }

    // picture check
    private boolean validateBlob(final MultipartFile hex) throws UploadException, SQLException {
        boolean success = (hex != null);

        if (success) {
            return true;
        }
        throw new UploadException("Invalid photo");
    }

    public int validateUpload(ProductRegistration productRegistration, User u) throws UploadException, SQLException {
        validateTitle(productRegistration.getTitle());
        validatePrice(productRegistration.getPrice());
        validateBlob(productRegistration.getPicture());
        java.util.Date date = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        productRegistration.setDate(sqlDate);

        if (productRegistration.getAlbum() == -1) {
            try {
                return this.context.uploadPhoto(u.getId(), productRegistration.getTitle(),
                        productRegistration.getPicture().getBytes(), productRegistration.getPrice(),
                        productRegistration.getIsPublic(), productRegistration.getDate());
            } catch (IOException e) {
                Logger.getLogger(UploadRepo.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        } else {
            try {
                return this.context.uploadPhotoWithAlbum(u.getId(), productRegistration.getTitle(),
                        productRegistration.getAlbum(), productRegistration.getPicture().getBytes(),
                        productRegistration.getPrice(), productRegistration.getIsPublic(), productRegistration.getDate());
            } catch (IOException e) {
                Logger.getLogger(UploadRepo.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }

        return -1;
    }
}
