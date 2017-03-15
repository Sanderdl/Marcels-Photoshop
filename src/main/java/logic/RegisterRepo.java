package logic;

import data.database.interfaces.IRegistrationContext;
import models.Registration;
import models.exceptions.InvalidRegisterException;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sande on 08/03/2017.
 */
public class RegisterRepo {
    private IRegistrationContext context;
    private Pattern pattern;
    private Matcher matcher;

    // for testing
    public RegisterRepo(IRegistrationContext context) {
        this.context = context;
    }

    private final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
            "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]" +
            "|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
    private final String VERIFIED = "verified";
    private final String NOT_VERIFIED = "not verified";
    private final String CUSTOMERROLE = "customer";
    private final String PHOTOGRAPHERROLE = "photographer";

    private void validateEmail(final String hex) throws InvalidRegisterException {
        pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(hex);

        boolean success = matcher.matches();

        if (!success) {
            throw new InvalidRegisterException("Not a valid email address");
        }
    }

    private void validatePassword(final String hex) throws InvalidRegisterException {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(hex);

        boolean success = matcher.matches();

        if (!success) {
            throw new InvalidRegisterException("Password must have stuff and things");
        }
    }

    private void validateName(final String hex) throws InvalidRegisterException {

        boolean success = (hex.length() > 1 && hex.length() <= 50);

        if (!success) {
            throw new InvalidRegisterException("Name is too long or too short");
        }
    }

    public void registerUser(Registration registration) throws InvalidRegisterException, SQLException {

        this.validateEmail(registration.getEmail());
        this.validatePassword(registration.getPassword());
        this.validateName(registration.getName());
        this.validateName(registration.getUserName());

        String verified = "";
        if (registration.getRole().toLowerCase().equals(this.CUSTOMERROLE.toLowerCase())) {
            verified = this.VERIFIED;
        } else if (registration.getRole().toLowerCase().equals(this.PHOTOGRAPHERROLE.toLowerCase())) {
            verified = this.NOT_VERIFIED;
        }

        this.context.registerUser(registration.getUserName(), registration.getPassword(), registration.getName(),
                registration.getEmail(), verified, registration.getRole());

    }


}
