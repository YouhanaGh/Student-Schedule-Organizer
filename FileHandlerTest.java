import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerTest {

    private static final String TEST_FILE_NAME = "tasks.csv";

    @Test
    public void testSaveAndLoadTasks() {
        // Create test tasks
        List<Task> testTasks = new ArrayList<>();
        testTasks.add(new Task("Task 1", "2023-05-01", "High", "This is a high priority task"));
        testTasks.add(new Task("Task 2", "2023-05-02", "Medium", "This is a medium priority task"));
        testTasks.get(0).setStatus("In Progress");
        testTasks.get(1).setStatus("Not Started");

        // Test saving tasks
        FileHandler.saveTasks(testTasks);

        // Verify file was created
        File file = new File(TEST_FILE_NAME);
        assertTrue(file.exists(), "File should exist after saving tasks");

        // Test loading tasks
        List<Task> loadedTasks = FileHandler.loadTasks();

        // Verify loaded tasks match original tasks
        assertEquals(testTasks.size(), loadedTasks.size(), "Loaded tasks count should match");
        for (int i = 0; i < testTasks.size(); i++) {
            assertEquals(testTasks.get(i).getName(), loadedTasks.get(i).getName(), "Task name should match");
            assertEquals(testTasks.get(i).getDueDate(), loadedTasks.get(i).getDueDate(), "Task due date should match");
            assertEquals(testTasks.get(i).getPriority(), loadedTasks.get(i).getPriority(), "Task priority should match");
            assertEquals(testTasks.get(i).getStatus(), loadedTasks.get(i).getStatus(), "Task status should match");
        }

        // Clean up: delete the test file
        file.delete();
    }

    @Test
    public void testLoadTasksFromEmptyFile() throws IOException {
        // Create empty file
        File file = new File(TEST_FILE_NAME);
        new FileWriter(file).close();

        // Test loading tasks from empty file
        List<Task> loadedTasks = FileHandler.loadTasks();
        // Verify no tasks were loaded
        assertTrue(loadedTasks.isEmpty(), "No tasks should be loaded from an empty file");

        // Clean up: delete the test file
        file.delete();
    }

    @Test
    public void testLoadTasksFileNotFound() {
        // Ensure test file doesn't exist
        File file = new File(TEST_FILE_NAME);
        file.delete();

        // Test loading tasks when file doesn't exist
        List<Task> loadedTasks = FileHandler.loadTasks();
        // Verify no tasks were loaded
        assertTrue(loadedTasks.isEmpty(), "No tasks should be loaded when file is not found");
    }
}
