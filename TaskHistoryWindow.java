/**
@author Swati Mehta & 3765212
*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TaskHistoryWindow {
    private JFrame historyFrame;
    private JTable historyTable;
    private DefaultTableModel historyTableModel;

    public TaskHistoryWindow(List<Task> completedTasks) {
        historyFrame = new JFrame("Task History");
        historyFrame.setSize(600, 400);
        historyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        historyFrame.setLayout(new BorderLayout());

        historyTableModel = new DefaultTableModel(new String[]{"Task", "Due Date", "Priority"}, 0);
        historyTable = new JTable(historyTableModel);
        JScrollPane scrollPane = new JScrollPane(historyTable);
        historyFrame.add(scrollPane, BorderLayout.CENTER);

        // Populate the table with completed tasks
        for (Task task : completedTasks) {
            historyTableModel.addRow(new Object[]{task.getName(), task.getDueDate(), task.getPriority()});
        }

        historyFrame.setVisible(true);
    }
}

