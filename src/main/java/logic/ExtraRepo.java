package logic;

import data.database.interfaces.IExtrasContext;
import models.Extra;
import models.exceptions.ExtraException;
import models.exceptions.InvalidRegisterException;
import models.exceptions.UploadException;

/**
 * Created by Tomt on 3-4-2017.
 */
public class ExtraRepo {
    private IExtrasContext context;

    public ExtraRepo(IExtrasContext context){ this.context= context;}

    private void validateName(final String hex) throws ExtraException {
        if (hex.length() < 1) {
            throw new ExtraException("Please enter a longer name.");
        } else if (hex.length() > 50) {
            throw new ExtraException("Please enter a shorter name.");
        }
    }

    private void validatePrice(final double hex) throws ExtraException {
        if (hex<0) {
            throw new ExtraException("Please enter a valid price.");
        } else if (hex>1000) {
            throw new ExtraException("Please enter a valid price.");
        }
    }
    
    public void deleteExtra(Extra extra) throws ExtraException {
        this.validateName(extra.getName());

        this.context.deleteExtra(extra.getName());
    }

    public void addExtra(Extra extra) throws ExtraException, UploadException {
        this.validateName(extra.getName());
        this.validatePrice(extra.getPrice());

        this.context.addNewExtraProduct(extra.getName(), extra.getPrice(), extra.getAvailable());
    }

    public void updateExtraAvailable(Extra extra) throws ExtraException, UploadException {
        this.validateName(extra.getName());

        this.context.changeAvailable(extra.getName(), extra.getAvailable());
    }

    public void updateExtraPrice(Extra extra) throws ExtraException, UploadException {
        this.validateName(extra.getName());
        this.validatePrice(extra.getPrice());

        this.context.changePrice(extra.getName(), extra.getPrice());
    }
}
