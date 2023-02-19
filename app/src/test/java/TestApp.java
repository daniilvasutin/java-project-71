import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class TestApp {

    private static ArrayList<Integer> testInstance;
    private static ListIterator<Integer> listIterator;

    private static Path file1;
    private static Path file2;

    @BeforeAll
    public static void setUp() {

        file1 = Path.of("file1.json");
        file2 = Path.of("file2.json");

        testInstance = new ArrayList<>();
        listIterator = testInstance.listIterator();
    }


    @Test
    public void testGenDiffForJSON() throws Exception {

        file1 = Path.of("file1.json");
        file2 = Path.of("file2.json");
        String str1 = Differ.generate(Parser.parseFile(file1, file2));
        assertEquals(str1, Differ.generate(Parser.parseFile(file1, file2)));
    }

// проверить парсер
// Напишите тесты. Тесты на вложенные структуры полностью покрывают плоские. >
// Последние можно уже не тестировать

    @Test
    public void testGenDiffForYML() throws Exception {

        file1 = Path.of("file1.yml");
        file2 = Path.of("file2.yml");
        String str1 = Differ.generate(Parser.parseFile(file1, file2));
        assertEquals(str1, Differ.generate(Parser.parseFile(file1, file2)));
    }

    @Test
    public void testFilePath() {

        String path = "src/test/resources";

        File file = new File(path);
        String absolutePath = file.getAbsolutePath();

        System.out.println(absolutePath);

        assertTrue(absolutePath.endsWith("src/test/resources"));
    }

    @Test
    public void testToArrayWhenInputArrayHaveSizeOne() {

        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[1];

        final Integer[] result = testInstance.toArray(input);
        assertNotEquals(input, result);
        assertEquals((Integer) 1, result[0]);
        assertEquals((Integer) 2, result[1]);
        assertEquals((Integer) 3, result[2]);
        assertEquals(3, result.length);
    }
}
