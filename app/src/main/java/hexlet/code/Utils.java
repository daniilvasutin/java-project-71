package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {

    public static String getFileExtension(String pathToFile) throws IOException {
        int index = pathToFile.lastIndexOf('.');
        if (index > 0) {
            return pathToFile.substring(index + 1);
        }
        throw new IOException("Path does not have extension");
    }

    public static String getContent(String filepath1) throws IOException {
        return Files.readString(Path.of(filepath1));
    }
}
