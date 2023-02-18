package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1",
            description = "path to first file", defaultValue = "src/test/resources/file1.json")
    ///Users/daniilvasutin/java-project-71/app/src/main/java/hexlet/code/exFile1.json
    private String file1;
    @Parameters(index = "1", paramLabel = "filepath2",
            description = "path to second file", defaultValue = "file2.json")
    private String file2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "";

    @Override
    public Integer call() throws Exception { // your business logic goes here...

        Path absolutPath1 = Paths.get(file1).toAbsolutePath().normalize();
        Path absolutPath2 = Paths.get(file2).toAbsolutePath().normalize();

        if (!Files.exists(absolutPath1) || !Files.exists(absolutPath2)) {
            throw new FileNotFoundException();
        }

        String differsOfFiles = Differ.generate(absolutPath1, absolutPath2);
        System.out.println(differsOfFiles);

        return 0;
    }

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

}
