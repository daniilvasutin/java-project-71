package hexlet.code.formatters;

import hexlet.code.Item;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String makeStylish(List<Item<String, Object>> sortedDifList) {

        StringBuilder result = new StringBuilder("{\n");

        for (var item : sortedDifList) {
            result.append(" ".repeat(4))
                    .append(item.status)
                    .append(" ")
                    .append(item.key)
                    .append(": ")
                    .append(item.currentValue)
                    .append("\n");
        }
        result.append("}");

        return result.toString();
    }

    public static String makeStylishMap(Map<String, Item<String, Object>> difMap) {

        StringBuilder result = new StringBuilder("{\n");

        for (var item : difMap.entrySet()) {
            if (item.getValue().status.equals("REMOVE")) {
                result.append(" ".repeat(4))
                        .append("-")
                        .append(" ")
                        .append(item.getValue().key)
                        .append(": ")
                        .append(item.getValue().currentValue)
                        .append("\n");
            }
            if (item.getValue().status.equals("UNCHANGED")) {
                result.append(" ".repeat(4))
                        .append(" ")
                        .append(" ")
                        .append(item.getValue().key)
                        .append(": ")
                        .append(item.getValue().currentValue)
                        .append("\n");
            }
            if (item.getValue().status.equals("CHANGED")) {
                result.append(" ".repeat(4))
                        .append("-")
                        .append(" ")
                        .append(item.getValue().key)
                        .append(": ")
                        .append(item.getValue().oldValue)
                        .append("\n");
                result.append(" ".repeat(4))
                        .append("+")
                        .append(" ")
                        .append(item.getValue().key)
                        .append(": ")
                        .append(item.getValue().currentValue)
                        .append("\n");
            }
            if (item.getValue().status.equals("ADD")) {
                result.append(" ".repeat(4))
                        .append("+")
                        .append(" ")
                        .append(item.getValue().key)
                        .append(": ")
                        .append(item.getValue().currentValue)
                        .append("\n");
            }

        }
        result.append("}");

        return result.toString();
    }
}
