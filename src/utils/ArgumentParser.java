package utils;

import java.util.HashMap;

public class ArgumentParser {

    public static boolean parseBooleanValue(HashMap<String, String> namedArgs, String[] args, String key, int posIndex, boolean defaultValue) {
        if (namedArgs.containsKey(key)) {
            return namedArgs.get(key).equalsIgnoreCase("on");
        } else if (posIndex < args.length && args[posIndex] != null) {
            return args[posIndex].equalsIgnoreCase("on");
        } else {
            return defaultValue;
        }
    }

    public static int parseIntValue(HashMap<String, String> namedArgs, String[] args, String key, int posIndex, int defaultValue) {
        if (namedArgs.containsKey(key)) {
            return Integer.parseInt(namedArgs.get(key));
        } else if (posIndex < args.length && args[posIndex] != null) {
            return Integer.parseInt(args[posIndex]);
        } else {
            return defaultValue;
        }
    }

    public static String parseStringValue(HashMap<String, String> namedArgs, String[] args, String key, int posIndex, String defaultValue) {
        if (namedArgs.containsKey(key)) {
            return namedArgs.get(key);
        } else if (posIndex < args.length && args[posIndex] != null) {
            return args[posIndex];
        } else {
            return defaultValue;
        }
    }
}
