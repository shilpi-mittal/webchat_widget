package common_utils;

// holds utils for value assignment
public class AssignmentUtils {
    // returns defaultValue if value is null, else returns value
    public static <T> T getValue(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }
}
