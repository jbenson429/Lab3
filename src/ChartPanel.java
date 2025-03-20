import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ChartPanel {
    public static void createChartFromCSV(String filePath) {
        List<String[]> data = loadData(filePath);

        if (data.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No data found in the file.");
            return;
        }

        String[] headers = data.get(0);
        List<String[]> rows = data.subList(1, data.size());

        JFrame frame = new JFrame("CSV Data Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int width = getWidth();
                int height = getHeight();
                int barWidth = width / rows.size();
                int maxHeight = height - 50;

                // Assuming numeric data in the second column
                g.setColor(Color.BLUE);
                try {
                    int maxValue = rows.stream()
                            .mapToInt(row -> Integer.parseInt(row[1].trim()))
                            .max()
                            .orElse(1);

                    for (int i = 0; i < rows.size(); i++) {
                        int value = Integer.parseInt(rows.get(i)[1].trim());
                        int barHeight = (int) ((double) value / maxValue * maxHeight);

                        g.fillRect(i * barWidth + 5, height - barHeight - 30, barWidth - 10, barHeight);
                        g.setColor(Color.BLACK);
                        g.drawString(rows.get(i)[0], i * barWidth + 10, height - 10);
                        g.setColor(Color.BLUE);
                    }

                } catch (NumberFormatException ex) {
                    g.drawString("Error: Non-numeric data in chart column!", 10, 20);
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }

    private static List<String[]> loadData(String filePath) {
        try {
            return Files.lines(Paths.get(filePath))
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load file: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
