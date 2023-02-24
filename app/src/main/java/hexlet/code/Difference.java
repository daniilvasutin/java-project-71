package hexlet.code;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Objects;

public class Difference {
    public static Map<String, Item> findDiff(Map<String, Object> map1, Map<String, Object> map2) {

        Map<String, Item> differ = new TreeMap<>();

        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (var key : allKeys) {
            Object oldValue = map1.get(key);
            Object newValue = map2.get(key);

            if (!map1.containsKey(key)) {
                differ.put(key, new Item(newValue, Status.ADD));
            } else if (!map2.containsKey(key)) {
                differ.put(key, new Item(oldValue, Status.REMOVED));
            } else if (Objects.equals(oldValue, newValue)) {
                differ.put(key, new Item(oldValue, newValue, Status.UNCHANGED));
            } else {
                differ.put(key, new Item(oldValue, newValue, Status.CHANGED));
            }
        }
        return differ;
    }
}
