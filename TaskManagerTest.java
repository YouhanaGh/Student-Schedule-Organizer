import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    private TaskManager taskManager;

    @BeforeEach
    public void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    public void testAddTask() {
        Task task1 = new Task("Study for Exam", "2025-03-14", "High", "Upcoming Exam");
        taskManager.addTask(task1);

        List<Task> tasks = taskManager.getTasks();
        assertEquals(1, tasks.size());
        assertEquals(task1, tasks.get(0));
    }

    @Test
    public void testRemoveTask() {
        Task task1 = new Task("Study for Exam", "2025-03-14", "High", "Upcoming Exam");
        Task task2 = new Task("Finish Assignment", "2025-03-14", "Medium", "Assignment is due");
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        taskManager.removeTask(2);

        List<Task> tasks = taskManager.getTasks();
        assertEquals(2, tasks.size());
        assertEquals(task2, tasks.get(1));
    }

    @Test
    public void testSortTasksByDate() {
        Task task1 = new Task("Study for Exam", "2025-03-14", "High", "Upcoming Exam");
        Task task2 = new Task("Finish Assignment", "2025-03-14", "Medium", "Assignment is due");
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        taskManager.sortTasksByDate();

        List<Task> tasks = taskManager.getTasks();
        assertEquals(task2, tasks.get(1)); // Earlier date should come first
        assertEquals(task1, tasks.get(0));
    }

    @Test
    public void testSortTasksByPriority() {
        Task task1 = new Task("Study for Exam", "2025-03-14", "High", "Upcoming Exam");
        Task task2 = new Task("Finish Assignment", "2025-03-14", "Low", "Assignment is due");
        Task task3 = new Task("Go to the gym", "2025-03-14", "Medium", "I have to go to the gym");
        
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        taskManager.sortTasksByPriority();

        List<Task> tasks = taskManager.getTasks();
        assertEquals(task2, tasks.get(0)); // Low priority last
        assertEquals(task3, tasks.get(1)); // Medium priority next
        assertEquals(task1, tasks.get(2)); // High priority last
    }

    @Test
    public void testDisplayTasks() {
        // This test verifies that the displayTasks method does not throw exceptions.
        // (You can manually check the console output to confirm the display format.)
        
        Task task1 = new Task("Study for Exam", "2025-03-14", "High", "Upcoming Exam");
        Task task2 = new Task("Finish Assignment", "2025-03-14", "Medium", "Assignment is due");

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        assertDoesNotThrow(() -> taskManager.displayTasks());
    }
}
