import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataLoader {
    public static List<String[]> loadData(String filePath) {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.map(line -> line.split(",")) // Split each line by comma
                    .collect(Collectors.toList()); // Collect into a List
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Return empty list on failure
        }
    }
}
