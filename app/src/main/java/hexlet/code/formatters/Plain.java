package hexlet.code.formatters;

import hexlet.code.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plain {

    public static String makePlain(List<Item<String, Object>> sortedDifList) {

        StringBuilder result = new StringBuilder("");
        for (var item : sortedDifList) {
            if (item.status.equals(" ")){
                continue;
            }

            String value = checkValue(item);
            String status = getCheckStatus(item.status);

            result.append("Property ").append("'").append(item.key).append("' ").append("");
        }
        result.append("}");

        return result.toString();
    }

    public static String makePlainMAP(Map<String, Item<String, Object>> differ) {

        StringBuilder result = new StringBuilder("");
        for (var item : differ.entrySet()) {

            if (item.getValue().status.equals("UNCHANGED")) {
                continue;
            }
            if (item.getValue().status.equals("REMOVE")) {
                result.append("Property ").append("'").append(item.getValue().key).append("' ")
                        .append("was removed").append("\n");
            }
            if (item.getValue().status.equals("ADD")) {
                result.append("Property ").append("'").append(item.getValue().key).append("' ")
                        .append("was added with value: ").append(checkValue(item.getValue().currentValue)).append("\n");
            }
            if (item.getValue().status.equals("CHANGED")) {
                result.append("Property ").append("'").append(item.getValue().key).append("' ")
                        .append("was updated. From ").append(checkValue(item.getValue().oldValue))
                        .append(" to ").append(checkValue(item.getValue().currentValue)).append("\n");
            }


            //result.append("Property ").append("'").append(item.key).append("' ").append("");

//            if (item.status.equals(" ")){
//                continue;
//            }

//            String value = checkValue(item);
//            String status = getCheckStatus(item.status);
//
//            result.append("Property ").append("'").append(item.key).append("' ").append("");
        }
        result.append("}");

        return result.toString();
    }

    private static String getCheckStatus(String status) {

        if (status.equals("+")) {
            return " was added with value: ";
        }
        if (status.equals("-")) {
            return " was removed";
        }
        if (status.equals("-")) {
            return " was updated. From ";
        }
        if (status.equals(" ")) {
            return "";
        }

        return "";
    }

    private static String checkValue(Object item) {
        if (item instanceof ArrayList<?> || item instanceof Map) {
            return "[complex value]";
        }
        if (item instanceof String){
            return "'" + item.toString() + "'";
        }
        return item != null ? item.toString() : "null";
    }


}
