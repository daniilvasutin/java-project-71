package hexlet.code;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.Callable;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {


    @Parameters(index = "0", paramLabel="filepath1", description = "path to first file", defaultValue = "file1.json") ///Users/daniilvasutin/java-project-71/app/src/main/java/hexlet/code/exFile1.json
    private File filepath1;// = new File("file1.json");
    @Parameters(index = "1", paramLabel="filepath2", description = "path to second file", defaultValue = "file2.json")
    private File filepath2;// = new File("file2.json");

//
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "";
//
    @Override
    public Integer call() throws Exception { // your business logic goes here...

        byte[] fileContents1 = Files.readAllBytes(filepath1.toPath());
        byte[] fileContents2 = Files.readAllBytes(filepath2.toPath());

        Differ.generate(fileContents1, fileContents2);

        return 0;
    }

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    public static class MyData<K, V> {
        public K key;
        public V value;
        public String status;

        public MyData(K item, V o, String status) {
            this.key = item;
            this.value = o;
            this.status = status;
        }

        public K getKey() {
            return key;
        }
    }

    public class Differ {
        public static String generate(byte[] contets1, byte[] contents2) throws Exception{

            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map1 = mapper.readValue(contets1, Map.class);
            System.out.print(">>>Sorted1>>>\t\t");
            System.out.println(map1.entrySet());

            Map<String, Object> map2 = mapper.readValue(contents2, Map.class);
            System.out.print(">>>Sorted2>>>\t\t");
            System.out.println(map2.entrySet());

            List<MyData<String, Object>> difList= new ArrayList<>();

            for (var key : map2.keySet()) {
                if (map1.containsKey(key)) {
                    if (map1.get(key).equals(map2.get(key))) { //значения равны
                        difList.add(new MyData<>(key, map1.get(key), " "));
                    } else
                    if (!map1.get(key).equals(map2.get(key))) { //значения НЕ равны
                        difList.add(new MyData<>(key, map1.get(key), "-"));
                        difList.add(new MyData<>(key, map2.get(key), "+"));
                    }
                }
                if (!map1.containsKey(key)) { // в первом нет ключа второго
                    difList.add(new MyData<>(key, map2.get(key), "+"));
                }
            }
            for (var key : map1.keySet()) {
                if (!map2.containsKey(key)) { //есть в первом но нет во втором
                    difList.add(new MyData<>(key, map1.get(key), "-"));
                }
            }

            List<MyData<String, Object>> sortedDifList= difList.stream()
                    .sorted(Comparator.comparing(MyData::getKey)).toList();

            String result = "{\n";
            for (var item : sortedDifList) {
                result = result + item.status + " " + item.key + ": " + item.value + "\n";
            }
            result += "}";

            System.out.println(result);

            return result;
        }
    }

}
