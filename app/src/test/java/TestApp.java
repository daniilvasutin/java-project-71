import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class TestApp {

    private static Path file1;
    private static Path file2;

    public static String getPath(String fileName) {
        return Paths.get("src", "test", "resources", fileName)
                .toFile().getAbsolutePath();
    }

    public final String getContent(String fileName) throws IOException {
        Path filePath = Path.of(getPath(fileName));
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void setUp() {
        file1 = Path.of(getPath("file1long.json"));
        file2 = Path.of(getPath("file2long.json"));
    }

    @Test
    public void testDiffJsonFormatStylish() throws Exception {

        String format = "stylish";

        String fileContent = getContent("TestStylish.txt");

        assertEquals(fileContent, Differ.generate(file1, file2));
        assertEquals(fileContent, Differ.generate(file1, file2, format));
        assertThat(Differ.generate(file1, file2, format)).isEqualTo(fileContent);
    }

    @Test
    public void testDiffJsonFormatPlain() throws Exception {

        String format = "plain";

        String fileContent = getContent("TestPlain.txt");
        String resultOfDiff = Differ.generate(file1, file2, format);

        assertThat(resultOfDiff).isEqualTo(fileContent);
       // assertEquals(fileContent, resultOfDiff);
    }

    @Test
    public void testDiffJsonFormatJson() throws Exception {

        String format = "json";

        String fileContent = getContent("TestJson.txt");
        String resultOfDiff = Differ.generate(file1, file2, format);

        assertEquals(fileContent, resultOfDiff);
    }

    @Test
    public void testDiffYMLFormatPlain() throws Exception {

        file1 = Path.of(getPath("file1long.yml"));
        file2 = Path.of(getPath("file2long.yml"));
        String format = "plain";

        String fileContent = getContent("TestPlain.txt");
        String resultOfDiff = Differ.generate(file1, file2, format);

        assertEquals(fileContent, resultOfDiff);
    }
}
