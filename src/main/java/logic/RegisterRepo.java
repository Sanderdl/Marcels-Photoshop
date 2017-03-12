package logic;

import data.database.IRegistrationContext;
import data.database.MySQLRegistrationContext;
import models.exceptions.InvalidRegisterException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sande on 08/03/2017.
 */
public class RegisterRepo {
    private IRegistrationContext context;
    private Pattern pattern;
    private Matcher matcher;

    public RegisterRepo(){
        this.context = new MySQLRegistrationContext();
    }

    // for testing
    public RegisterRepo(IRegistrationContext context){
        this.context = context;
    }

    private final String EMAIL_PATTERN = "^(.+)@(.+)$";
    private final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    public boolean validateEmail(final String hex) throws InvalidRegisterException{
        pattern = Pattern.compile(EMAIL_PATTERN ,  Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(hex);

        boolean success = matcher.matches();

        if (!success){
            throw new InvalidRegisterException("Not a valid email adress");
        }

        return success;
    }

    public boolean validatePassword(final String hex) throws InvalidRegisterException{
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(hex);

        boolean success = matcher.matches();

        if (!success){
            throw new InvalidRegisterException("Password must have stuff and things");
        }

        return success;
    }

    public boolean validateUsername(final String hex) throws InvalidRegisterException{

        boolean success = (hex.length() > 1 && hex.length() <= 50 );

        if (!success){
            throw new InvalidRegisterException("Username too long or too short");
        }

        return success;
    }


}
