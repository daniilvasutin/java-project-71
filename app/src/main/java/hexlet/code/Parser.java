package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parseFile(String content, String extension) throws Exception {

        return switch (extension) {
            case "json" -> parseJson(content);
            case "yml", "yaml" -> parseYml(content);
            default -> throw new Exception("Unknown extension: " + extension);
        };
    }

    private static Map<String, Object> parseJson(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<HashMap<String, Object>>() { });
    }

    private static Map<String, Object> parseYml(String content) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, new TypeReference<HashMap<String, Object>>() { });
    }
}
