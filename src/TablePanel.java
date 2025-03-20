import javax.swing.*;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
//"src/All_Track_Lines_Final.csv"
public class TablePanel {
    public static void main(String[] args) {
        String filePath = "src/All_Track_Lines_Final.csv"; // Ensure this path is correct
        List<String[]> data = DataLoader.loadData(filePath);

        if (data.isEmpty()) {
            System.out.println("No data found.");
            return;
        }

        // Extract column headers dynamically
        String[] headers = data.get(0);
        System.out.println("Detected Columns: " + Arrays.toString(headers));

        // Use streams to process and filter out header row
        List<String[]> rows = data.stream().skip(1).collect(Collectors.toList());

        DataProcessor.printDataDetails(headers, rows);

        SwingUtilities.invokeLater(() -> DataGUI.createAndShowGUI(headers, rows));
        //fix up
    }
}
