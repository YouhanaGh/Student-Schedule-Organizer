import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

import java.util.List;

public class CalendarView extends JFrame {
    private List<Task> tasks;
    private JPanel calendarPanel;
    private JLabel monthLabel;
    private int currentMonthOffset = 0; // Tracks the current month (0 = current month, 1 = next month, etc.)

    public CalendarView(List<Task> tasks) {
        this.tasks = tasks;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Calendar View");
        setSize(1000, 600); // Increase window width to accommodate all days
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Month Navigation Panel
        JPanel navigationPanel = new JPanel();
        JButton prevMonthButton = new JButton("<< Previous Month");
        JButton nextMonthButton = new JButton("Next Month >>");
        monthLabel = new JLabel("", SwingConstants.CENTER);
        monthLabel.setFont(new Font("Arial", Font.BOLD, 16));

        prevMonthButton.addActionListener(e -> navigateMonth(-1));
        nextMonthButton.addActionListener(e -> navigateMonth(1));

        navigationPanel.add(prevMonthButton);
        navigationPanel.add(monthLabel);
        navigationPanel.add(nextMonthButton);
        add(navigationPanel, BorderLayout.NORTH);

        // Calendar Panel (Grid Layout for Days of the Week)
        calendarPanel = new JPanel(new GridLayout(0, 7, 5, 5)); // Multiple rows, 7 columns, with small gaps
        calendarPanel.setPreferredSize(new Dimension(950, 500)); // Set preferred size for the calendar panel
        add(new JScrollPane(calendarPanel), BorderLayout.CENTER);

        updateMonthDisplay(); // Display the current month's tasks
        setVisible(true);
    }

    private void navigateMonth(int offset) {
        currentMonthOffset += offset;
        updateMonthDisplay();
    }

    private void updateMonthDisplay() {
        calendarPanel.removeAll(); // Clear the current display

        // Get the current month and year based on the offset
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, currentMonthOffset);
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Set to the first day of the month
        Date firstDayOfMonth = calendar.getTime();

        // Update the month label
        DateFormat monthFormat = new SimpleDateFormat("MMMM yyyy");
        monthLabel.setText(monthFormat.format(firstDayOfMonth));

        // Create the days of the week header
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : daysOfWeek) {
            JLabel dayHeader = new JLabel(day, SwingConstants.CENTER);
            dayHeader.setFont(new Font("Arial", Font.BOLD, 14));
            calendarPanel.add(dayHeader);
        }

        // Calculate the number of days in the month and the start day of the month
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Day of the week (0 = Sunday, 1 = Monday, etc.)
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Add empty cells for the days before the 1st of the month
        for (int i = 0; i < startDay; i++) {
            calendarPanel.add(new JPanel()); // Empty panel to align days correctly
        }

        // Add the actual days of the month
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int day = 1; day <= daysInMonth; day++) {
            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
            dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            dayPanel.setPreferredSize(new Dimension(120, 100)); // Set fixed width for each day panel

            // Add the day number
            JLabel dayLabel = new JLabel(String.valueOf(day), SwingConstants.CENTER);
            dayLabel.setFont(new Font("Arial", Font.BOLD, 14));
            dayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            dayPanel.add(dayLabel);

            // Add tasks for this day
            for (Task task : tasks) {
                try {
                    Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(task.getDueDate());
                    Calendar taskCalendar = Calendar.getInstance();
                    taskCalendar.setTime(dueDate);

                    // Check if this task's due date matches the current day
                    if (taskCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                        taskCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                        taskCalendar.get(Calendar.DAY_OF_MONTH) == day) {

                        JLabel taskLabel = new JLabel("<html>" + task.toString() + "</html>"); // Use HTML for multi-line text
                        taskLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                        taskLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center-align the task label
                        if (dueDate.before(new Date())) { // Highlight overdue tasks
                            taskLabel.setForeground(Color.RED);
                        }
                        dayPanel.add(taskLabel);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            calendarPanel.add(dayPanel);
        }

        calendarPanel.revalidate();
        calendarPanel.repaint();
    }
}

