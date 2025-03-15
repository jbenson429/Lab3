import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DataGUI {
    public static void createAndShowGUI(String[] headers, List<String[]> data) {
        JFrame frame = new JFrame("CSV Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        DefaultTableModel model = new DefaultTableModel(headers, 0);

        // Use Streams to add rows to the table model
        data.stream().forEach(model::addRow);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
