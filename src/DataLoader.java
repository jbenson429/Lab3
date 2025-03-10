import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class DataVisualization {
    public static void main(String[] args) {
        String filePath = "All_Track_Lines_Final.csv"; // Update with actual file path
        List<String[]> data = readData(filePath);

        if (data.isEmpty()) {
            System.out.println("No data found.");
            return;
        }

        // Print details about specific entries
        printDataDetails(data);

        // Launch GUI
        SwingUtilities.invokeLater(() -> createAndShowGUI(data));
    }