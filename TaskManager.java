import java.util.*;

/**
 * The TaskManager class is responsible for managing a list of tasks.
 * It provides methods to add, remove, sort, and display tasks.
 */
public class TaskManager {

    // List to store tasks
    private List<Task> tasks = new ArrayList<>();
    /**
     * Adds a new task to the task list.
     * 
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list based on the task ID.
     * 
     * @param taskId The ID of the task to be removed.
     */
    public void removeTask(int taskId) {
        tasks.removeIf(task -> task.getId() == taskId);
    }
     /**
     * Sorts tasks by their due date in ascending order and displays the sorted tasks.
     */
    public void sortTasksByDate() {
        tasks.sort(Comparator.comparing(Task::getDueDate));
        displayTasks();
    }

    /**
     * Sorts tasks by their priority in descending order (High to Low) and displays the sorted tasks.
     */
    public void sortTasksByPriority() {
        tasks.sort(Comparator.comparing(task -> getPriorityValue(task.getPriority()), Comparator.reverseOrder()));
        displayTasks();
    }

    /**
     * Returns an integer representation of a priority level for sorting purposes.
     * 
     * @param priority The string representation of the task's priority.
     * @return The integer value representing the priority (1 for High, 2 for Medium, 3 for Low).
     */
    private int getPriorityValue(String priority) {
        if (priority.equalsIgnoreCase("High")) return 1;
        if (priority.equalsIgnoreCase("Medium")) return 2;
        if (priority.equalsIgnoreCase("Low")) return 3;
        return Integer.MAX_VALUE; // Default case scenrio
    }

     /**
     * Returns the list of tasks managed by the TaskManager.
     * 
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }
    
    /**
     * Displays all tasks in the task list.
     * This method prints each task's details to the console.
     */
    public void displayTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
