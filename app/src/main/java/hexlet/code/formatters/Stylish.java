package hexlet.code.formatters;

import hexlet.code.Item;
import java.util.Map;

public class Stylish {

    public static String makeStylish(Map<String, Item> difMap) {

        StringBuilder result = new StringBuilder("{\n");

        for (var item : difMap.entrySet()) {

            switch (item.getValue().status) {
                case "REMOVED" -> result.append(" ".repeat(2)).append("-")
                        .append(" ").append(item.getKey())
                        .append(": ").append(item.getValue().newValue)
                        .append("\n");
                case "UNCHANGED" -> result.append(" ".repeat(2)).append(" ")
                        .append(" ").append(item.getKey())
                        .append(": ").append(item.getValue().newValue)
                        .append("\n");
                case "CHANGED" ->
                    result.append(" ".repeat(2))
                            .append("-").append(" ").append(item.getKey())
                            .append(": ").append(item.getValue().oldValue)
                            .append("\n").append(" ".repeat(2))
                            .append("+").append(" ").append(item.getKey())
                            .append(": ").append(item.getValue().newValue)
                            .append("\n");
                case "ADD" -> result.append(" ".repeat(2))
                        .append("+").append(" ")
                        .append(item.getKey()).append(": ")
                        .append(item.getValue().newValue).append("\n");
                default -> throw new IllegalStateException("Unexpected status: " + item.getValue().status);
            }
        }
        result.append("}");

        return result.toString();
    }
}
