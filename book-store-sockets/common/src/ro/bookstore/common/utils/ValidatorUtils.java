package ro.bookstore.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nicu on 3/14/2017.
 */
public class ValidatorUtils {
    private static String patternForName = "^([\\pL]{3,}[ |-]?)*$";


    private static Pattern namePattern = Pattern.compile(patternForName);

    /**
     * Function validated a name using a regex pattern.
     * @param name name to be validated
     * @return true if the name is correct, false otherwise
     */
    public static Boolean isValidName(String name) {
        if (name == null) {
            return false;
        }

        Matcher matcher = namePattern.matcher(name);

        return matcher.find();
    }

    /**
     * Functions validated a number if it's greater or equal with 0.
     * @param number number to be validated
     * @return true if the number is not negative, false otherwise
     */
    public static Boolean isValidPositiveNumber(Number number) {
        return number != null && number.longValue() >= 0;
    }
}
