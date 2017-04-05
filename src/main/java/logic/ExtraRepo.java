package logic;

import data.database.interfaces.IExtrasContext;
import models.Extra;
import models.exceptions.ExtraException;
import models.exceptions.UploadException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Tomt on 3-4-2017.
 */
public class ExtraRepo {
    private IExtrasContext context;
    private Properties props;
    private InputStream input;

    public ExtraRepo(IExtrasContext context){
        this.context= context;
        this.props = new Properties();
    }

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
    
    public void deleteExtra(int id) throws ExtraException {

        this.context.deleteExtra(id);
    }

    public void addExtra(Extra extra) throws ExtraException, UploadException {
        this.validateName(extra.getName());
        this.validatePrice(extra.getPrice());

        this.context.addNewExtraProduct(extra.getName(), extra.getPrice(), extra.getAvailable());
        this.addExtrasToProperties(extra.getName());
    }

    public void updateExtraAvailable(int id, boolean avail) throws ExtraException, UploadException {

        this.context.changeAvailable(id, avail);
    }

    public void updateExtraPrice(int id, double prize) throws ExtraException, UploadException {
        this.validatePrice(prize);

        this.context.changePrice(id, prize);
    }

    private void addExtrasToProperties(String name) throws ExtraException {
        try {
            input = new FileInputStream("values.properties");
            props.load(input);
            String key = "extra."+name.toLowerCase();
            props.setProperty(key, name);
            String test = props.getProperty("extra.cap");
            System.out.println(test);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new ExtraException("Adding to properties failed!");
        }
    }
}
