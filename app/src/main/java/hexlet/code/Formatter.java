package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String makeString(List<Item<String, Object>> differ, String format) {


        if (format.equals("stylish")) {
            return Stylish.makeStylish(differ);
        }

        if (format.equals("plain")) {
            return Plain.makePlain(differ);
        }

        return "";
    }

    public static String makeStringMap(Map<String, Item<String, Object>> differ, String format) {


        if (format.equals("stylish")) {
            return Stylish.makeStylishMap(differ);
        }

        if (format.equals("plain")) {
            return Plain.makePlainMAP(differ);
        }

        return "";
    }
}
