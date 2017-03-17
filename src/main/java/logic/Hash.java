package logic;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by sande on 15/03/2017.
 */
public class Hash {
    public static String passHash(String p) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(p);

    }

    public static boolean validatePass(String p, String hash){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(p,hash);
    }
}
