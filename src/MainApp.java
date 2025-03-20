import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        // Launch StatsPanel GUI
        SwingUtilities.invokeLater(() -> {
            StatsPanel statsPanel = new StatsPanel();
            statsPanel.show();
        });

        // Launch TablePanel functionality
        TablePanel.main(args);

        // Launch the chart window using the CSV file
        ChartPanel.createChartFromCSV("src/All_Track_Lines_Final.csv");
    }
}
