package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {

    public static String convertToFormatString(Map<String, Item> differ, String format) throws JsonProcessingException {

        return switch (format) {
            case "stylish" -> Stylish.makeStylish(differ);
            case "plain" -> Plain.makePlain(differ);
            case "json" -> Json.makeJson(differ);
            default -> throw new IllegalStateException("Unexpected format: " + format);
        };
    }
}
