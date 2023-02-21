package hexlet.code.formatters;

import hexlet.code.Item;
import java.util.Map;

public class Stylish {

    public static String makeStylish(Map<String, Item> difMap) {

        StringBuilder result = new StringBuilder("{\n");

        for (var item : difMap.entrySet()) {

            switch (item.getValue().getStatus()) {
                case "REMOVED" -> result.append(" ".repeat(2)).append("-")
                        .append(" ").append(item.getKey())
                        .append(": ").append(item.getValue().getNewValue())
                        .append("\n");
                case "UNCHANGED" -> result.append(" ".repeat(2)).append(" ")
                        .append(" ").append(item.getKey())
                        .append(": ").append(item.getValue().getNewValue())
                        .append("\n");
                case "CHANGED" ->
                    result.append(" ".repeat(2))
                            .append("-").append(" ").append(item.getKey())
                            .append(": ").append(item.getValue().getOldValue())
                            .append("\n").append(" ".repeat(2))
                            .append("+").append(" ").append(item.getKey())
                            .append(": ").append(item.getValue().getNewValue())
                            .append("\n");
                case "ADD" -> result.append(" ".repeat(2))
                        .append("+").append(" ")
                        .append(item.getKey()).append(": ")
                        .append(item.getValue().getNewValue()).append("\n");
                default -> throw new IllegalStateException("Unexpected status: " + item.getValue().getStatus());
            }
        }
        result.append("}");

        return result.toString();
    }
}
