import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.swing.table.TableCellRenderer;

/**
@author Jatin Vig 3751583 
@author Swati Mehta 3765212
*/
public class TaskManagerGUI {
    private JFrame frame;
    private PreferencesManager preferencesManager;
    private JTextField nameField, idField, descriptionField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordBox;
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private JButton completeBtn, historyBtn;
    private JComboBox<String> priorityBox;
    private JSpinner dueDateSpinner;
    private JLabel welcomeLabel;
    private JCheckBox darkModeBox;
    private List<Task> completedTasks = new ArrayList<>();
    private String studentName;
    private String studentId;
    private final String TASK_FILE_PREFIX = "tasks_";
    private final String CREDENTIAL_FILE = "credentials.txt";
    private final String HISTORY_FILE_PREFIX = "history_";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TaskManagerGUI::new);
    }

    public TaskManagerGUI() {
        initializeLoginScreen();
    }

    private void initializeLoginScreen() {
        frame = new JFrame("Student Task Manager");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        ImageIcon bgIcon = new ImageIcon("First.jpeg");
        Image scaledImg = bgIcon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImg));
        backgroundLabel.setLayout(null);

        JPanel loginPanel = new JPanel(null);
        loginPanel.setBounds(240, 130, 320, 300);
        loginPanel.setBackground(new Color(255, 255, 255, 220));

        JLabel titleLabel = new JLabel("Login / Register", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setBounds(60, 10, 200, 30);
        loginPanel.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 60, 80, 25);
        loginPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 60, 200, 25);
        loginPanel.add(nameField);

        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(20, 100, 80, 25);
        loginPanel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 100, 200, 25);
        loginPanel.add(idField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20, 140, 80, 25);
        loginPanel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 140, 200, 25);
        loginPanel.add(passwordField);

        showPasswordBox = new JCheckBox("Show Password");
        showPasswordBox.setBounds(100, 170, 200, 20);
        showPasswordBox.setOpaque(false);
        showPasswordBox.addActionListener(e -> passwordField.setEchoChar(showPasswordBox.isSelected() ? (char) 0 : '*'));
        loginPanel.add(showPasswordBox);

        
        JButton loginBtn = new JButton("Login / Register");
        loginBtn.setBounds(100, 210, 200, 30);
        loginBtn.addActionListener(e -> handleLogin());
        loginPanel.add(loginBtn);

        backgroundLabel.add(loginPanel);
        frame.setContentPane(backgroundLabel);
        frame.setVisible(true);
    }

    private void handleLogin() {
        studentName = nameField.getText().trim();
        studentId = idField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (!studentName.matches("[a-zA-Z ]+") || studentName.isEmpty()) {
            showError("Invalid name. Only letters and spaces allowed.");
            return;
        }

        if (!studentId.matches("\\d+")) {
            showError("Student ID must be numeric.");
            return;
        }

        if (password.isEmpty()) {
            showError("Password cannot be empty.");
            return;
        }

        boolean isNew = validateOrRegisterUser(studentId, password);
        if (!isNew && !new File(CREDENTIAL_FILE).exists()) return;

        if (isNew) {
            JOptionPane.showMessageDialog(frame, "New registration successful!");
        }

        frame.setContentPane(new JPanel());
        frame.getContentPane().removeAll();
        frame.repaint();
        showMainDashboard();
    }

    private boolean validateOrRegisterUser(String id, String password) {
        try {
            File file = new File(CREDENTIAL_FILE);
            if (!file.exists()) file.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            boolean userExists = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(id)) {
                    userExists = true;
                    if (!parts[1].equals(password)) {
                        showError("Incorrect password.");
                        reader.close();
                        return false;
                    }
                }
            }
            reader.close();

            if (!userExists) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(id + "," + password);
                writer.newLine();
                writer.close();
                return true;
            }
        } catch (IOException e) {
            showError("Error accessing credentials.");
            return false;
        }
        return false;
    }

    private void showMainDashboard() {
        frame.getContentPane().removeAll();
        frame.setLayout(null);

        welcomeLabel = new JLabel("Welcome, " + studentName + "!", SwingConstants.CENTER);
        welcomeLabel.setBounds(200, 10, 400, 30);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        frame.add(welcomeLabel);

        tableModel = new DefaultTableModel(new String[]{"Task", "Due Date", "Priority", "Description"}, 0);
        taskTable = new JTable(tableModel) {
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        String priority = (String) getValueAt(row, 2); // priority is column 2

        if (priority != null) {
            switch (priority.toLowerCase()) {
                case "high":
                    c.setBackground(new Color(255, 102, 102)); // light red
                    break;
                case "medium":
                    c.setBackground(new Color(255, 255, 153)); // yellow
                    break;
                case "low":
                    c.setBackground(new Color(153, 255, 153)); // green
                    break;
                default:
                    c.setBackground(Color.WHITE);
            }
        } else {
            c.setBackground(Color.WHITE);
        }

        if (isRowSelected(row)) {
            c.setBackground(c.getBackground().darker());
        }

        return c;
    }
};
        
        JScrollPane scrollPane = new JScrollPane(taskTable);
        scrollPane.setBounds(50, 50, 680, 150);
        frame.add(scrollPane);

        JTextField taskNameField = new JTextField();
        taskNameField.setBounds(50, 220, 120, 25);
        frame.add(taskNameField);

        dueDateSpinner = new JSpinner(new SpinnerDateModel());
        dueDateSpinner.setEditor(new JSpinner.DateEditor(dueDateSpinner, "yyyy-MM-dd"));
        dueDateSpinner.setBounds(180, 220, 120, 25);
        frame.add(dueDateSpinner);

        priorityBox = new JComboBox<>(new String[]{"High", "Medium", "Low"});
        priorityBox.setBounds(310, 220, 100, 25);
        frame.add(priorityBox);

        descriptionField = new JTextField();
        descriptionField.setBounds(420, 220, 310, 25);
        frame.add(descriptionField);

        JButton addBtn = new JButton("Add Task");
        addBtn.setBounds(50, 260, 150, 25);
        addBtn.addActionListener(e -> {
            String task = taskNameField.getText();
            String date = new java.text.SimpleDateFormat("yyyy-MM-dd").format((Date) dueDateSpinner.getValue());
            String priority = (String) priorityBox.getSelectedItem();
            String desc = descriptionField.getText().trim();

            if (task.toLowerCase().contains("midterm") || task.toLowerCase().contains("project")) {
                priority = "High";
                JOptionPane.showMessageDialog(frame, "This looks important! Priority set to High.");
            }

            tableModel.addRow(new String[]{task, date, priority, desc});
            taskNameField.setText("");
            descriptionField.setText("");
            saveTasksToFile();
            
            
        
        });
        frame.add(addBtn);

        JButton deleteBtn = new JButton("Delete Task");
        deleteBtn.setBounds(210, 260, 150, 25);
        deleteBtn.addActionListener(e -> deleteSelectedTasks());
        frame.add(deleteBtn);

        completeBtn = new JButton("Complete");
        completeBtn.setBounds(370, 260, 150, 25);
        completeBtn.addActionListener(e -> completeSelectedTask());
        frame.add(completeBtn);

        historyBtn = new JButton("View History");
        historyBtn.setBounds(530, 260, 150, 25);
        historyBtn.addActionListener(e -> showHistory());
        frame.add(historyBtn);
        
        
        preferencesManager = new PreferencesManager(frame, tableModel, taskTable, welcomeLabel);
        preferencesManager.addSortingDropdown(frame);  // Add sorting dropdown
	preferencesManager.addDarkModeCheckBox(frame);
    
	
        JButton calendarButton = new JButton("Calendar View");
        calendarButton.setBounds(210, 300, 150, 25);
        calendarButton.addActionListener(e -> openCalendarView());
        frame.add(calendarButton);

        loadTasksFromFile();
        checkForUpcomingTasks();

        frame.repaint();
        frame.revalidate();
    }

    private void completeSelectedTask() {
        int selectedRow = taskTable.getSelectedRow();
        if (selectedRow == -1) {
            showError("Please select a task to mark as complete.");
            return;
        }

        String taskName = (String) tableModel.getValueAt(selectedRow, 0);
        String dueDate = (String) tableModel.getValueAt(selectedRow, 1);
        String priority = (String) tableModel.getValueAt(selectedRow, 2);

        completedTasks.add(new Task(taskName, dueDate, priority));
        tableModel.removeRow(selectedRow);

        saveTasksToFile();
        saveCompletedTasksToFile();
    }

    private void saveCompletedTasksToFile() {
	FileHandler.saveCompletedTasksToFile(studentId, completedTasks);
    }

    private void showHistory() {
        new TaskHistoryWindow(completedTasks);
    }


    private void checkForUpcomingTasks() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            LocalDate dueDate = LocalDate.parse((String) tableModel.getValueAt(i, 1), formatter);
            if (!dueDate.isBefore(today)) continue;
            JOptionPane.showMessageDialog(frame, "Task '" + tableModel.getValueAt(i, 0) + "' is overdue!", "Reminder", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveTasksToFile() {
	FileHandler.saveTasksToFile(studentId, tableModel);
    }

    private void loadTasksFromFile() {
	FileHandler.loadTasksFromFile(studentId, tableModel);
    }

    private void deleteSelectedTasks() {
        int[] selectedRows = taskTable.getSelectedRows();
        if (selectedRows.length == 0) {
            showError("Please select a task to delete.");
            return;
        }
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            tableModel.removeRow(selectedRows[i]);
        }
        saveTasksToFile();
    }

    private void openCalendarView() {
        new CalendarView(getTasksFromTable());
    }

    private List<Task> getTasksFromTable() {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String taskName = (String) tableModel.getValueAt(i, 0);
            String dueDate = (String) tableModel.getValueAt(i, 1);
            String priority = (String) tableModel.getValueAt(i, 2);
            tasks.add(new Task(taskName, dueDate, priority));
        }
        return tasks;
    }
    


    private void showError(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
