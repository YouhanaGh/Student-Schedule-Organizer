/**
@author Swati Mehta & 3765212
*/


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class PreferencesManager {
    private JFrame frame;
    private DefaultTableModel tableModel;
    private boolean isDarkMode = false;
    private JCheckBox darkModeBox; // Declare darkModeBox here
    private JTable taskTable; // Declare taskTable 
    private JLabel welcomeLabel;

    public PreferencesManager(JFrame frame, DefaultTableModel tableModel, JTable taskTable, JLabel welcomeLabel) {
        this.frame = frame;
        this.tableModel = tableModel;
        this.taskTable = taskTable; // Set taskTable
        this.welcomeLabel = welcomeLabel; // Set welcomeLabel
    }


    // Method to add sorting dropdown
    public void addSortingDropdown(JFrame frame) {
        JComboBox<String> sortBox = new JComboBox<>(new String[]{"Sort by Priority", "Sort by Due Date"});
        sortBox.setBounds(370, 300, 150, 25);
        sortBox.addActionListener(e -> sortTasks((String) sortBox.getSelectedItem()));
        frame.add(sortBox);
    }

    public void addDarkModeCheckBox(JFrame frame) {
    	darkModeBox = new JCheckBox("Dark Mode");
        darkModeBox.setBounds(50, 300, 150, 25);
        darkModeBox.addActionListener(e -> toggleTheme());
        frame.add(darkModeBox);
        }

    // Method to toggle between light and dark mode
    private void toggleTheme() {
        boolean dark = darkModeBox.isSelected();
        frame.getContentPane().setBackground(dark ? Color.DARK_GRAY : null);
        taskTable.setBackground(dark ? Color.GRAY : Color.WHITE);
        welcomeLabel.setForeground(dark ? Color.WHITE : Color.BLACK);
    }
    
    // Method to sort tasks
    private void sortTasks(String criteria) {
        List<Object[]> rows = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String task = (String) tableModel.getValueAt(i, 0);
            LocalDate date = LocalDate.parse((String) tableModel.getValueAt(i, 1), formatter);
            String priority = (String) tableModel.getValueAt(i, 2);
            String description = (String) tableModel.getValueAt(i, 3);
            rows.add(new Object[]{task, date, priority, description});
        }

        if (criteria.equals("Sort by Due Date")) {
            rows.sort((a, b) -> ((LocalDate) a[1]).compareTo((LocalDate) b[1]));
        } else if (criteria.equals("Sort by Priority")) {
            List<String> priorityOrder = List.of("High", "Medium", "Low");
            rows.sort((a, b) -> Integer.compare(priorityOrder.indexOf(a[2]), priorityOrder.indexOf(b[2])));
        }

        tableModel.setRowCount(0);
        for (Object[] row : rows) {
            tableModel.addRow(new Object[]{row[0], row[1].toString(), row[2], row[3]});
        }
    }
}
