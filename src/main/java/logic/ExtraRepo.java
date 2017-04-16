package logic;

import data.database.interfaces.IExtrasContext;
import models.Extra;
import models.exceptions.ExtraException;
import models.exceptions.UploadException;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Tomt on 3-4-2017.
 */

public class ExtraRepo {
    private IExtrasContext context;
    private InputStream input;

    @Resource(name = "myProperties")
    private Properties props;

    public ExtraRepo(IExtrasContext context) {
        this.context = context;
        input = getClass().getResourceAsStream("values.properties");
    }

    private void validateName(final String hex) throws ExtraException {
        if (hex.length() < 1) {
            throw new ExtraException("Please enter a longer name.");
        } else if (hex.length() > 50) {
            throw new ExtraException("Please enter a shorter name.");
        }
    }

    private void validatePrice(final double hex) throws ExtraException {
        if (hex < 0 || hex > 1000) {
            throw new ExtraException("Please enter a valid price.");
        }
    }

    public void deleteExtra(int id) throws ExtraException {

        this.context.deleteExtra(id);
    }

    public void addExtra(Extra extra) throws ExtraException, UploadException {
        //this.validateName(extra.getName());
        //this.validatePrice(extra.getPrice());

       // this.context.addNewExtraProduct(extra.getName(), extra.getPrice(), extra.getAvailable());
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

        String key = "extra." + name.toLowerCase();
        try {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        props.setProperty(key, name);
        String test = props.getProperty(key);
        System.out.println(test);

    }
}
