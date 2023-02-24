package hexlet.code;

import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {

        Map<String, Object> map1 = Parser.parseFile(Utils.getContent(filepath1), Utils.getFileExtension(filepath1));
        Map<String, Object> map2 = Parser.parseFile(Utils.getContent(filepath2), Utils.getFileExtension(filepath2));

        Map<String, Item> differ = Difference.findDiff(map1, map2);

        return Formatter.convertToFormat(differ, format);
    }

}
