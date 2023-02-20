package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1",
            description = "path to first file", defaultValue = "src/test/resources/file1long.json")
    private static Path filepath1;
    @Parameters(index = "1", paramLabel = "filepath2",
            description = "path to second file", defaultValue = "src/test/resources/file2long.json")
    private static Path filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]", defaultValue = "stylish")
    private String format;

    @Override
    public Integer call() throws Exception {

        String differsOfFiles = Differ.generate(filepath1, filepath2, format);
        System.out.println(differsOfFiles);

        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
