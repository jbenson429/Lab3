import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class StatsPanel {
    private JFrame frame;
    private JTextArea outputArea;
    private static final String FILE_PATH = "src/All_Track_Lines_Final.csv";

    public StatsPanel() {
        frame = new JFrame("CSV Statistics Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JButton loadButton = new JButton("Load CSV");
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        loadButton.addActionListener(e -> loadCSV());

        frame.setLayout(new BorderLayout());
        frame.add(loadButton, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    public void show() {
        frame.setVisible(true);
    }

    private void loadCSV() {
        try {
            String stats = computeStatistics(FILE_PATH);
            outputArea.setText(stats);
        } catch (Exception ex) {
            outputArea.setText("Error loading file: " + ex.getMessage());
        }
    }

    private String computeStatistics(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        if (lines.isEmpty()) return "Empty file";

        String[] headers = lines.get(0).split(",");
        Map<String, List<Double>> columnData = new HashMap<>();
        for (String header : headers) {
            columnData.put(header, new ArrayList<>());
        }

        for (int i = 1; i < lines.size(); i++) {
            String[] values = lines.get(i).split(",");
            for (int j = 0; j < headers.length && j < values.length; j++) {
                try {
                    double value = Double.parseDouble(values[j]);
                    columnData.get(headers[j]).add(value);
                } catch (NumberFormatException ignored) {
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (String column : columnData.keySet()) {
            List<Double> values = columnData.get(column);
            if (!values.isEmpty()) {
                double min = Collections.min(values);
                double max = Collections.max(values);
                double avg = values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                result.append(String.format("%s - Min: %.2f, Max: %.2f, Avg: %.2f\n", column, min, max, avg));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StatsPanel statsPanel = new StatsPanel();
            statsPanel.show();
        });
    }
}
