package hexlet.code.formatters;

import hexlet.code.Item;
import java.util.ArrayList;
import java.util.Map;

public class Plain {

    public static String makePlain(Map<String, Item> differ) {

        StringBuilder result = new StringBuilder();
        for (var item : differ.entrySet()) {

            switch (item.getValue().status) {
                case "REMOVED" -> result.append("Property ").append("'").append(item.getKey())
                        .append("' ").append("was removed").append("\n");
                case "ADD" -> result.append("Property ").append("'").append(item.getKey())
                        .append("' ").append("was added with value: ")
                        .append(checkValue(item.getValue().newValue)).append("\n");
                case "CHANGED" -> result.append("Property ").append("'").append(item.getKey())
                        .append("' ").append("was updated. From ")
                        .append(checkValue(item.getValue().oldValue)).append(" to ")
                        .append(checkValue(item.getValue().newValue)).append("\n");
                case "UNCHANGED" -> result.append("");
                default -> throw new IllegalStateException("Unexpected status: " + item.getValue().status);
            }
        }

        return result.toString();
    }

    private static String checkValue(Object item) {
        if (item instanceof ArrayList<?> || item instanceof Map) {
            return "[complex value]";
        }
        if (item instanceof String) {
            return "'" + item + "'";
        }
        return item != null ? item.toString() : "null";
    }


}
