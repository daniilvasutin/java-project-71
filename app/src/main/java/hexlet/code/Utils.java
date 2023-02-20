package hexlet.code;

import java.nio.file.Path;

public class Utils {
    public static final boolean equalsWithNulls(Object a, Object b) {
        if (a == b) {
            return true;
        }
        if ((a == null) || (b == null)) {
            return false;
        }
        return a.equals(b);
    }

    public static final String getFileExtension(Path pathToFile) {
        int index = pathToFile.toString().lastIndexOf('.');
        return index > 0 ? pathToFile.toString().substring(index + 1) : "";
    }
}
