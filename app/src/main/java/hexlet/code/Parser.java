package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Parser {

    public static List<Map<String, Object>> parseFile(Path file1, Path file2) throws IOException {

        Path absolutPath1 = file1.toAbsolutePath().normalize();
        Path absolutPath2 = file2.toAbsolutePath().normalize();

        if (!Files.exists(absolutPath1) || !Files.exists(absolutPath2)) {
            throw new FileNotFoundException();
        }

        ObjectMapper mapper = new ObjectMapper();
        if (absolutPath1.toString().endsWith(".json") && absolutPath2.toString().endsWith(".json")) {
            mapper = new ObjectMapper(); //new YAMLMapper();
            mapper.findAndRegisterModules();
        }

        if (absolutPath1.toString().endsWith(".yml") && absolutPath2.toString().endsWith(".yml")) {
            mapper = new ObjectMapper(new YAMLFactory()); //new YAMLMapper();
            mapper.findAndRegisterModules();
        }

        byte[] fileContents1 = Files.readAllBytes(file1);
        byte[] fileContents2 = Files.readAllBytes(file2);
        Map<String, Object> map1 = mapper.readValue(fileContents1, Map.class);
        Map<String, Object> map2 = mapper.readValue(fileContents2, Map.class);

        return List.of(map1, map2);
    }
}
