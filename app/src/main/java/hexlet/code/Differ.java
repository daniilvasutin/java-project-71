package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Differ {

    public static String generate(Path file1, Path file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    public static String generate(Path file1, Path file2, String format) throws Exception {

        Path absolutPath1 = file1.toAbsolutePath().normalize();
        Path absolutPath2 = file2.toAbsolutePath().normalize();

        byte[] fileContents1 = Files.readAllBytes(absolutPath1);
        byte[] fileContents2 = Files.readAllBytes(absolutPath2);

        String extension1 = Utils.getFileExtension(absolutPath1);
        String extension2 = Utils.getFileExtension(absolutPath2);

        Map<String, Object> map1 = Parser.parseFile(fileContents1, extension1);
        Map<String, Object> map2 = Parser.parseFile(fileContents2, extension2);

        Map<String, Item> differ = findDiff(map1, map2);

        return Formatter.convertToFormat(differ, format);
    }

    private static Map<String, Item> findDiff(Map<String, Object> map1, Map<String, Object> map2) {

        Map<String, Item> differ = new TreeMap<>();

        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (var key : allKeys) {
            Object oldValue = map1.get(key);
            Object newValue = map2.get(key);

            if (map1.containsKey(key)) {
                if (Utils.equalsWithNulls(oldValue, newValue)) { //значения равны
                    differ.put(key, new Item(oldValue, newValue, "UNCHANGED"));
                } else { //значения НЕ равны
                    differ.put(key, new Item(oldValue, newValue, "CHANGED"));
                }
            }
            if (!map1.containsKey(key)) { // в первом нет ключа второго
                differ.put(key, new Item(newValue, "ADD"));
            }
            if (!map2.containsKey(key)) { //есть в первом но нет во втором
                differ.put(key, new Item(oldValue, "REMOVED"));
            }

        }
        return differ;
    }
}
