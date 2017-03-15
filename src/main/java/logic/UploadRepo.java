package logic;

import com.sun.deploy.util.SessionState;
import data.database.interfaces.IUploadContext;
import models.ProductRegistration;
import models.User;
import models.exceptions.UploadException;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by Tomt on 13-3-2017.
 */
public class UploadRepo {
    private IUploadContext context;

    private boolean validateTitle(final String hex) throws UploadException{
        boolean success= (hex.length()>1&&hex.length()<=50);

        if(success){
            return true;
        }
        throw new UploadException("Invalid name length");
    }

    private boolean validatePrice(final double hex) throws UploadException{
        boolean success = (hex>1&& hex<1000);

        if(success) {
            return true;
        }
        throw new UploadException("Invalid price");
    }

    // picture check
    private boolean validateBlob(final Blob hex) throws UploadException, SQLException{
        boolean success = (hex.length()>0);

        if (success){
            return true;
        }
        throw new UploadException("Invalid photo");
    }

    public void validateUpload(ProductRegistration productregistration,User u) throws UploadException, SQLException{
        validateTitle(productregistration.getTitle());
        validatePrice(productregistration.getPrice());

        //this.context.uploadPhoto(u.getId(),productregistration.getTitle(),productregistration.getAlbum(),productregistration.getPicture(),productregistration.getPrice(),productregistration.getIsPublic(),productregistration.getDate());
    }
}
