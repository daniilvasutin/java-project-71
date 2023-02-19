package hexlet.code;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(List<Map<String, Object>> parsedFiles) throws Exception {

        List<Item<String, Object>> difList = findDiff(parsedFiles.get(0), parsedFiles.get(1));

        List<Item<String, Object>> sortedDifList = difList.stream()
                .sorted(Comparator.comparing(Item::getKey)).toList();

        return diffToString(sortedDifList, "stylish");
    }

    public static String diffToString(List<Item<String, Object>> sortedDifList, String format) {

        StringBuilder result = new StringBuilder("{\n");
        if (format.equals("stylish")) {
            for (var item : sortedDifList) {
                result.append(" ".repeat(4))
                        .append(item.status)
                        .append(" ")
                        .append(item.key)
                        .append(": ")
                        .append(item.value)
                        .append("\n");
            }
            result.append("}");
        }

        return result.toString();
    }

    private static List<Item<String, Object>> findDiff(Map<String, Object> map1, Map<String, Object> map2) {

        List<Item<String, Object>> difList = new ArrayList<>();
        for (var key : map2.keySet()) {
            if (map1.containsKey(key)) {
                var item1 = map1.get(key);
                var item2 = map2.get(key);
                if (Utils.equalsWithNulls(item1, item2)) { //значения равны
                    difList.add(new Item<>(key, map1.get(key), " "));
                } else if (!Utils.equalsWithNulls(item1, item2)) { //значения НЕ равны
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
