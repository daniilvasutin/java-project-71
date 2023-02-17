import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;


public class TestApp {

    private static ArrayList<Integer> testInstance;
    private static ListIterator<Integer> listIterator;

    private static File file1;
    private static File file2;

    @BeforeAll
    public static void setUp() {

        file1 = new File("file1.json");
        file2 = new File("file2.json");

        testInstance = new ArrayList<>();
        listIterator = testInstance.listIterator();
    }


    @Test
    public void testGenDiff() throws Exception {

        String str1 = Differ.generate(file1, file2);

        assertEquals(str1, Differ.generate(file1, file2));
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
