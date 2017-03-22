package logic;

import data.database.interfaces.IRegistrationContext;
import models.Registration;
import models.User;
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
    private final String hexPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
    private final String CUSTOMERROLE = "customer";
    private final String PHOTOGRAPHERROLE = "photographer";

    private void validateEmail(final String hex) throws InvalidRegisterException {
        pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(hex);

        boolean success = matcher.matches();

        if (!success) {
            throw new InvalidRegisterException("Please enter a valid email address.");
        }
    }

    private void validatePassword(final String hex) throws InvalidRegisterException {
        pattern = Pattern.compile(hexPattern);
        matcher = pattern.matcher(hex);

        boolean success = matcher.matches();

        if (!success) {
            throw new InvalidRegisterException("Password must have at least 8 characters, 1 lowercase letter," +
                    " 1 uppercasee letter and 1 number.");
        }
    }

    private void validateName(final String hex) throws InvalidRegisterException {

        if (hex.length() < 1) {
            throw new InvalidRegisterException("Please enter a longer name.");
        } else if (hex.length() > 50) {
            throw new InvalidRegisterException("Please enter a shorter name.");
        }
    }

    public void registerUser(Registration registration) throws InvalidRegisterException, SQLException {

        this.validateEmail(registration.getEmail());
        this.validatePassword(registration.getPassword());
        this.validateName(registration.getName());
        this.validateName(registration.getUserName());

        String verified = "";
        if (registration.getRole().equalsIgnoreCase(this.CUSTOMERROLE)) {
            verified = User.UserStatus.verified.toString();
        } else if (registration.getRole().equalsIgnoreCase(this.PHOTOGRAPHERROLE)) {
            verified = User.UserStatus.not_verified.toString();
        }

        this.context.registerUser(registration, verified);

    }


}
