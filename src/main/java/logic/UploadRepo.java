package logic;

import models.exceptions.UploadException;

import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by Tomt on 13-3-2017.
 */
public class UploadRepo {
    private void validateName(final String hex) throws UploadException{
        boolean success= (hex.length()>1&&hex.length()<=50);

        if(!success){
            throw new UploadException("Invalid name length");
        }
    }

    private void validatePrice(final int hex) throws UploadException{
        boolean success = (hex>1&& hex<1000);

        if(!success) {
            throw new UploadException("Invalid price");
        }
    }

    // picture check
    private void validateBlob(final Blob hex) throws UploadException, SQLException{
        boolean success = (hex.length()>0);

        if (!success){
            throw new UploadException("Invalid photo");
        }
    }
}
