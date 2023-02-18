package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(File file1, File file2) throws Exception {

        byte[] fileContents1 = Files.readAllBytes(file1.toPath());
        byte[] fileContents2 = Files.readAllBytes(file2.toPath());

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(fileContents1, Map.class);
        Map<String, Object> map2 = mapper.readValue(fileContents2, Map.class);

        //hellow

        List<Item<String, Object>> difList = findDiff(map1, map2);

        List<Item<String, Object>> sortedDifList = difList.stream()
                .sorted(Comparator.comparing(Item::getKey)).toList();

        return diffToString(sortedDifList);
    }

    private static String diffToString(List<Item<String, Object>> sortedDifList) {

        StringBuilder result = new StringBuilder("{\n");
        for (var item : sortedDifList) {
            result.append(item.status).append(" ").append(item.key).append(": ").append(item.value).append("\n");
        }
        result.append("}");
        return result.toString();
    }

    private static List<Item<String, Object>> findDiff(Map<String, Object> map1, Map<String, Object> map2) {

        List<Item<String, Object>> difList = new ArrayList<>();
        for (var key : map2.keySet()) {
            if (map1.containsKey(key)) {
                if (map1.get(key).equals(map2.get(key))) { //значения равны
                    difList.add(new Item<>(key, map1.get(key), " "));
                } else if (!map1.get(key).equals(map2.get(key))) { //значения НЕ равны
                    difList.add(new Item<>(key, map1.get(key), "-"));
                    difList.add(new Item<>(key, map2.get(key), "+"));
                }
            }
            if (!map1.containsKey(key)) { // в первом нет ключа второго dsa
                difList.add(new Item<>(key, map2.get(key), "+"));
            }
        }
        for (var key : map1.keySet()) {
            if (!map2.containsKey(key)) { //есть в первом но нет во втором
                difList.add(new Item<>(key, map1.get(key), "-"));
            }
        }
        return difList;
    }


}
