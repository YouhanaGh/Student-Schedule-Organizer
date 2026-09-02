import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * This test verifies that course-related tasks are correctly filtered 
 * by a specific due date — just like selecting a day in a calendar view.
 *
 * Author: swati & 3765212
 */
public class CalendarViewTest {

    /**
     * Creates a  list of course-related tasks with different due dates.
     * @return a list of sample Task objects representing CS course assignments
     */
    private List<Task> getSampleCourseTasks() {
        List<Task> tasks = new ArrayList<>();

        // Sample tasks with course codes
        tasks.add(new Task("CS2043", "2025-03-23", "High"));   
        tasks.add(new Task("CS1073", "2025-03-23", "Medium")); 
        tasks.add(new Task("CS1303", "2025-03-25", "Low"));   
        tasks.add(new Task("CS1083", "2025-03-27", "Medium")); 
        tasks.add(new Task("CS1203", "2025-03-20", "High"));   

        return tasks;
    }

    /**
     * Filters the given list of tasks to return only those due on a specific date.
     * @param tasks List of tasks to filter
     * @param targetDate The date to match (format: YYYY-MM-DD)
     * @return List of tasks due on the given date
     */
    private List<Task> getTasksDueOn(String targetDate, List<Task> tasks) {
        List<Task> filtered = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDueDate().equals(targetDate)) {
                filtered.add(task);
            }
        }
        return filtered;
    }

    /**
     * Test to verify that tasks for CS2043 and CS1073 are correctly returned 
     * when filtering for due date 2025-03-23.
     */
    @Test
    public void testTasksDueOnMarch23() {
        List<Task> allTasks = getSampleCourseTasks();
        List<Task> tasksDue = getTasksDueOn("2025-03-23", allTasks);

        // Expecting two course tasks on this date
        Assert.assertEquals("Expected 2 tasks due on 2025-03-23", 2, tasksDue.size());

        boolean hasCS2043 = false;
        boolean hasCS1073 = false;

        for (Task task : tasksDue) {
            if (task.getName().equals("CS2043")) hasCS2043 = true;
            if (task.getName().equals("CS1073")) hasCS1073 = true;
        }

        Assert.assertTrue("Expected task from CS2043 to be due", hasCS2043);
        Assert.assertTrue("Expected task from CS1073 to be due", hasCS1073);
    }
}
