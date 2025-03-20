import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class ChartPanel {
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
        frame.setSize(800, 600);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int width = getWidth();
                int height = getHeight();
                int padding = 10;
                int barWidth = (width - (padding * (rows.size() + 1))) / rows.size();
                int maxHeight = height - 100;

                g.setColor(Color.BLUE);
                try {
                    int maxValue = rows.stream()
                            .mapToInt(row -> Integer.parseInt(row[1].trim()))
                            .max()
                            .orElse(1);

                    for (int i = 0; i < rows.size(); i++) {
                        int value = Integer.parseInt(rows.get(i)[1].trim());
                        int barHeight = (int) ((double) value / maxValue * maxHeight);

                        int x = padding + i * (barWidth + padding);
                        int y = height - barHeight - 50;

                        g.setColor(Color.BLUE);
                        g.fillRect(x, y, barWidth, barHeight);

                        g.setColor(Color.BLACK);
                        g.drawString(rows.get(i)[0], x, height - 30);
                        g.drawString(String.valueOf(value), x, y - 5);
                    }

                } catch (NumberFormatException ex) {
                    g.drawString("Error: Non-numeric data in chart column!", 10, 20);
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);
        panel.repaint();
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
