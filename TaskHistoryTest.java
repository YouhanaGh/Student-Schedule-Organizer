import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * This class tests that tasks are correctly marked as completed.
 * 
 * @author: Jatin Vig 3751583
 */
public class TaskHistoryTest {

    /**
     * Creates a few sample tasks and marks them as completed.
     * @return a list of completed Task objects for testing
     */
    private List<Task> createCompletedSampleTasks() {
        List<Task> tasks = new ArrayList<>();

        // Adding a few course-related tasks
        tasks.add(new Task("CS2263", "2025-03-25", "High"));
        tasks.add(new Task("CS2043", "2025-03-20", "Medium"));
        tasks.add(new Task("CS1073", "2025-03-22", "Low"));

        // Mark all tasks as completed
        for (Task task : tasks) {
            task.markAsCompleted();
        }

        return tasks;
    }

    /**
     * This test checks whether all sample tasks are correctly marked as completed.
     */
    @Test
    public void allTasksShouldBeMarkedAsCompleted() {
        List<Task> completedTasks = createCompletedSampleTasks();

        for (Task task : completedTasks) {
            Assert.assertTrue("Expected task to be marked as completed", task.isCompleted());
            Assert.assertEquals("Expected task status to be 'Completed'", "Completed", task.getStatus());
        }
    }

    /**
     * This test checks whether specific course tasks are present in the completed task list.
     */
    @Test
    public void completedTasksShouldContainExpectedCourses() {
        List<Task> completedTasks = createCompletedSampleTasks();

        boolean foundCS2263 = false;
        boolean foundCS2043 = false;
        boolean foundCS1073 = false;

        for (Task task : completedTasks) {
            switch (task.getName()) {
                case "CS2263": foundCS2263 = true; break;
                case "CS2043": foundCS2043 = true; break;
                case "CS1073": foundCS1073 = true; break;
            }
        }

        // Now we check if all expected course tasks were found
        Assert.assertTrue("Expected to find task: CS2263", foundCS2263);
        Assert.assertTrue("Expected to find task: CS2043", foundCS2043);
        Assert.assertTrue("Expected to find task: CS1073", foundCS1073);
    }
}
