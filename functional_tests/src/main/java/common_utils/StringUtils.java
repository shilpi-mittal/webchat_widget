package common_utils;

// holds utils for string manipulation
public class StringUtils {
    // splits input string into array by given splitByRegex and returns the array of strings
    public static String[] getStringArray(String splitByRegex, String input) {
        return input.split(splitByRegex);
    }
}
