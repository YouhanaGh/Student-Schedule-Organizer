import java.io.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/** 
@author Jatin Vig 3751583
*/
public class FileHandler {
    private static final String TASK_FILE_PREFIX = "tasks_";
    private static final String HISTORY_FILE_PREFIX = "history_";

    public static void saveTasksToFile(String studentId, DefaultTableModel tableModel) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TASK_FILE_PREFIX + studentId + ".csv"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                writer.println(tableModel.getValueAt(i, 0) + "," +
                        tableModel.getValueAt(i, 1) + "," +
                        tableModel.getValueAt(i, 2) + "," +
                        tableModel.getValueAt(i, 3));
            }
        } catch (IOException e) {
            e.printStackTrace(); // or show error message
        }
    }

    public static void loadTasksFromFile(String studentId, DefaultTableModel tableModel) {
        File file = new File(TASK_FILE_PREFIX + studentId + ".csv");
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length == 4) {
                    tableModel.addRow(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // or show error message
        }
    }

    public static void saveCompletedTasksToFile(String studentId, List<Task> completedTasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(HISTORY_FILE_PREFIX + studentId + ".csv"))) {
            for (Task task : completedTasks) {
                writer.println(task.getName() + "," + task.getDueDate() + "," + task.getPriority());
            }
        } catch (IOException e) {
            e.printStackTrace(); // or show error message
        }
    }
}
