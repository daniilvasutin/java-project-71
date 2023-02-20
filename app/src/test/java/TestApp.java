import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

public class TestApp {

    private static Path file1;
    private static Path file2;

    @BeforeAll
    public static void setUp() {

        file1 = Path.of("src/test/resources/file1long.json");
        file2 = Path.of("src/test/resources/file2long.json");
    }

    @Test
    public void testDiffJsonFormatStylish() throws Exception {

        String format = "stylish";

        String fileContent = Files.readString(Path.of("src/test/resources/TestStylish.txt"));

        assertEquals(fileContent, Differ.generate(file1, file2, format));
        assertThat(Differ.generate(file1, file2, format)).isEqualTo(fileContent);
    }

    @Test
    public void testDiffJsonFormatPlain() throws Exception {

        String format = "plain";

        String fileContent = Files.readString(Path.of("src/test/resources/TestPlain.txt"));
        String resultOfDiff = Differ.generate(file1, file2, format);

        assertEquals(fileContent, resultOfDiff);
    }

    @Test
    public void testDiffJsonFormatJson() throws Exception {

        String format = "json";

        String fileContent = Files.readString(Path.of("src/test/resources/TestJson.txt"));
        String resultOfDiff = Differ.generate(file1, file2, format);

        assertEquals(fileContent, resultOfDiff);
    }

    @Test
    public void testDiffYMLFormatPlain() throws Exception {

        file1 = Path.of("src/test/resources/file1long.yml");
        file2 = Path.of("src/test/resources/file2long.yml");
        String format = "plain";

        String fileContent = Files.readString(Path.of("src/test/resources/TestPlain.txt"));
        String resultOfDiff = Differ.generate(file1, file2, format);

        assertEquals(fileContent, resultOfDiff);
    }
}
