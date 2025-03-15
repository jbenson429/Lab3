import java.util.List;
import java.util.Arrays;

public class DataProcessor {
    public static void printDataDetails(String[] headers, List<String[]> data) {
        System.out.println("Column Headers: " + Arrays.toString(headers));
        System.out.println("Total Rows: " + data.size());

        if (data.size() >= 1) {
            System.out.println("\nAttributes of 1st Item:");
            System.out.println(Arrays.toString(data.get(0)));
        }

        if (data.size() >= 10) {
            System.out.println("\nAttributes of 10th Item:");
            System.out.println(Arrays.toString(data.get(9))); // Index 9 for the 10th item
        }
    }
}
