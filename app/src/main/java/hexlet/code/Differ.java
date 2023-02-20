package hexlet.code;


import java.util.*;

public class Differ {
    public static String generate(List<Map<String, Object>> parsedFiles, String format) throws Exception {

        List<Item<String, Object>> difList = findDiff(parsedFiles.get(0), parsedFiles.get(1));

        Map<String, Item<String, Object>> mapDiffer = findDiffMap(parsedFiles.get(0), parsedFiles.get(1));


        List<Item<String, Object>> sortedDifList = difList.stream()
                .sorted(Comparator.comparing(Item::getKey)).toList();

        return Formatter.makeString(sortedDifList, format);
    }

    public static String generateMap(List<Map<String, Object>> parsedFiles, String format) throws Exception {

        //List<Item<String, Object>> difList = findDiff(parsedFiles.get(0), parsedFiles.get(1));

        Map<String, Item<String, Object>> mapDiffer = findDiffMapAllKey(parsedFiles.get(0), parsedFiles.get(1));


//        List<Item<String, Object>> sortedDifList = difList.stream()
//                .sorted(Comparator.comparing(Item::getKey)).toList();

        return Formatter.makeStringMap(mapDiffer, format);
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
                        .append(item.currentValue)
                        .append("\n");
            }
            result.append("}");
        }

        return result.toString();
    }

    //map down work
    private static Map<String, Item<String, Object>> findDiffMap(Map<String, Object> map1, Map<String, Object> map2) {

        Map<String, Item<String, Object>> differ = new TreeMap<>();

        for (var key : map2.keySet()) {
            if (map1.containsKey(key)) {
                var item1 = map1.get(key);
                var item2 = map2.get(key);
                var oldvalue = map1.get(key);
                var newvalue = map2.get(key);
                if (Utils.equalsWithNulls(item1, item2)) { //значения равны
                    differ.put(key, new Item<>(key, oldvalue, oldvalue, "UNCHENGED"));
                } else if (!Utils.equalsWithNulls(item1, item2)) { //значения НЕ равны
                    differ.put(key, new Item<>(key, newvalue, oldvalue, "CHANGED"));
                }
            }
            if (!map1.containsKey(key)) { // в первом нет ключа второго dsa
                differ.put(key, new Item<>(key, map2.get(key), map2.get(key), "ADD"));
            }
        }
        for (var key : map1.keySet()) {
            if (!map2.containsKey(key)) { //есть в первом но нет во втором
                differ.put(key, new Item<>(key, map1.get(key), "REMOVE"));
            }
        }
        return differ;
    }
    //map up work

    private static Map<String, Item<String, Object>> findDiffMapAllKey(Map<String, Object> map1, Map<String, Object> map2) {

        Map<String, Item<String, Object>> differ = new TreeMap<>();

        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (var key : allKeys){
            if (map1.containsKey(key)) {
                var item1 = map1.get(key);
                var item2 = map2.get(key);
                var oldvalue = map1.get(key);
                var newvalue = map2.get(key);
                if (Utils.equalsWithNulls(item1, item2)) { //значения равны
                    differ.put(key, new Item<>(key, oldvalue, oldvalue, "UNCHANGED"));
                } else if (!Utils.equalsWithNulls(item1, item2)) { //значения НЕ равны
                    differ.put(key, new Item<>(key, newvalue, oldvalue, "CHANGED"));
                }
            }
            if (!map1.containsKey(key)) { // в первом нет ключа второго dsa
                differ.put(key, new Item<>(key, map2.get(key), map2.get(key), "ADD"));
            }
            if (!map2.containsKey(key)) { //есть в первом но нет во втором
                differ.put(key, new Item<>(key, map1.get(key), "REMOVE"));
            }
        }

//        for (var key : map2.keySet()) {
//            if (map1.containsKey(key)) {
//                var item1 = map1.get(key);
//                var item2 = map2.get(key);
//                var oldvalue = map1.get(key);
//                var newvalue = map2.get(key);
//                if (Utils.equalsWithNulls(item1, item2)) { //значения равны
//                    differ.put(key, new Item<>(key, oldvalue, oldvalue, "UNCHENGED"));
//                } else if (!Utils.equalsWithNulls(item1, item2)) { //значения НЕ равны
//                    differ.put(key, new Item<>(key, newvalue, oldvalue, "CHANGED"));
//                }
//            }
//            if (!map1.containsKey(key)) { // в первом нет ключа второго dsa
//                differ.put(key, new Item<>(key, map2.get(key), map2.get(key), "ADD"));
//            }
//        }
//        for (var key : map1.keySet()) {
//            if (!map2.containsKey(key)) { //есть в первом но нет во втором
//                differ.put(key, new Item<>(key, map1.get(key), "REMOVE"));
//            }
//        }
        return differ;
    }

    private static List<Item<String, Object>> findDiff(Map<String, Object> map1, Map<String, Object> map2) {

//        List<String, Item<String, Object>> differ = new ArrayList<>();
//
//        for (var key : map2.keySet()) {
//            if (map1.containsKey(key)) {
//                var item1 = map1.get(key);
//                var item2 = map2.get(key);
//                var oldvalue = map1.get(key);
//                var newvalue = map2.get(key);
//                if (Utils.equalsWithNulls(item1, item2)) { //значения равны
//                    differ.put(key, new Item<>(key, oldvalue, oldvalue, "UNCHENGED"));
//                } else if (!Utils.equalsWithNulls(item1, item2)) { //значения НЕ равны
//                    differ.put(key, new Item<>(key, oldvalue, newvalue, "CHANGED"));
//                }
//                if (!map1.containsKey(key)) { // в первом нет ключа второго dsa
//                    differ.put(key, new Item<>(key, newvalue, newvalue, "ADD"));
//                }
//            }
//        }
//        for (var key : map1.keySet()) {
//            if (!map2.containsKey(key)) { //есть в первом но нет во втором
//                differ.put(key, new Item<>(key, map1.get(key), "REMOVE"));
//            }
//        }
//        return differ;
        //map up



        //list work
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
        //list up
    }


}
