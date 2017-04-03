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

    public int validateUpload(ProductRegistration productRegistration, User u) throws UploadException, SQLException {
        java.util.Date date = Calendar.getInstance().getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        productRegistration.setDate(sqlDate);
        try
        {
            byte[] bytes = productRegistration.getPicture().getBytes();
            // Validate
            if(validateTitle(productRegistration.getTitle()) && validatePrice(productRegistration.getPrice())
                    && validateBlob(productRegistration.getPicture()) && validateFileSize(bytes)){
                if (productRegistration.getAlbum() == -1)
                {
                    return this.context.uploadPhoto(u.getId(), productRegistration.getTitle(),
                            bytes, productRegistration.getPrice(),
                            productRegistration.getIsPublic(), productRegistration.getDate());
                }
                else
                {
                    return this.context.uploadPhotoWithAlbum(u.getId(), productRegistration.getTitle(),
                            productRegistration.getAlbum(), bytes,
                            productRegistration.getPrice(), productRegistration.getIsPublic(), productRegistration.getDate());
                }
            }
        }
        catch (IOException e)
        {
            Logger.getLogger(UploadRepo.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            throw new UploadException("An error occurred while retrieving the size of your picture, please try again.");
        }
        return -1;
    }

    private boolean validateTitle(final String hex) throws UploadException {
        boolean success = (hex.length() > 1 && hex.length() <= 50);

        if (success)
        {
            return true;
        }
        throw new UploadException("The title of your picture must be between 1 and 50 characters long.");
    }

    private boolean validatePrice(final double hex) throws UploadException {
        boolean success = (hex > 0 && hex < 1000);

        if (success)
        {
            return true;
        }
        throw new UploadException("The price of your picture must be at least €0.00 and not above €1000.00");
    }

    // picture check
    private boolean validateBlob(final MultipartFile hex) throws UploadException {
        boolean success = (hex != null);

        if (success)
        {
            return true;
        }
        throw new UploadException("The uploaded picture is empty, please select a picture.");
    }

    private boolean validateFileSize(byte[] bytes) throws UploadException{
        if (bytes.length > 64000)
        {
            throw new UploadException("The chosen picture is too large. The maximum permitted size is 64kb");
        }
        return true;
    }

}
