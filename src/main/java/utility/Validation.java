package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static final Pattern PASSWORD;
    private static final Pattern NAME;
    private static final Pattern EMAIL;
    private static final Pattern IMAGE;
    private static final Pattern TEXT;
    private static final Pattern DATE_STRING;

    static {
        PASSWORD = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$");
        NAME = Pattern.compile("^[a-zA-Z ]{3,15}$");
        EMAIL =Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        IMAGE = Pattern.compile(".*\\.(jpg|jpeg)$");
        TEXT = Pattern.compile("[a-zA-Z0-9., ]{5,100}$");
        DATE_STRING = Pattern.compile("^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$");
    }

    public static boolean isValidPassword(String password) {
        Matcher matcher = PASSWORD.matcher(password);
        return matcher.find();
    }

    public static boolean isValidName(String name){
        Matcher matcher = NAME.matcher(name);
        return  matcher.find();
    }

    public static boolean isValidEmail(String email){
        Matcher matcher = EMAIL.matcher(email);
        return matcher.find();
    }

    public static boolean isValidImage(String image){
        Matcher matcher = IMAGE.matcher(image);
        return matcher.find();
    }

    public static boolean isValidText(String description){
        Matcher matcher = TEXT.matcher(description);
        return matcher.find();
    }

    public static boolean isValidDateString(String dateString){
        Matcher matcher = DATE_STRING.matcher(dateString);
        return matcher.find();
    }

}
