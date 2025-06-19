package org.bookstore.client.util;

public class Validator {
    public static final String IS_INTEGER = "^[0-9]+$";

    public static boolean isValid(String regex, String input) {
        return input.matches(regex);
    }

    public static boolean isNull(Object ...objects) {
        for (Object object : objects) {
            if (object == null || object.toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
