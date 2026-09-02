/**
 * The Task class represents an individual task with attributes such as ID, name, 
 * due date, priority, status, and completion status.
 * It provides methods to retrieve and modify task details.
 */
public class Task {
    private int id;// Unique identifier for the task
    private static int idCounter = 1;// Static counter to auto-increment task IDs
    private String name;// Name or title of the task
    private String dueDate;// Due date for task completion
    private String priority;// Priority level (High, Medium, Low)
    private String status;// Status of the task (Pending, Completed, etc.)
    private boolean completed;// Indicates whether the task is completed
    private String description; // description of the Task
    /**
     * Constructor to initialize a new Task object.
     * @param name - The name of the task.
     * @param dueDate - The due date of the task.
     * @param priority - The priority level of the task.
     * @param description - A short description of the task.
     */
    public Task(String name, String dueDate, String priority,String description){
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = "Pending";// Default status when a task is created
        this.completed = false;
        this.id = idCounter++; // Assign unique ID and increment counter
        this.description = description;
    }
    // Getter methods to retrieve task attributes

    /**
     * @return The unique ID of the task.
     */
    public int getId(){
        return id;
    }
      /**
     * @return The name/title of the task.
     */
    public String getName(){
        return name;
    }
    /**
     * @return The due date of the task.
     */
    public String getDueDate(){
        return dueDate;
    }
    /**
     * @return The priority level of the task.
     */
    public String getPriority(){
        return priority;
    }
     /**
     * @return The current status of the task.
     */
    public String getStatus(){
        return status;
    }
    public String getDescription() {
        return description;
    }
    // Setter methods to update task attributes

    /**
     * Sets the status of the task.
     * @param status - The new status of the task (e.g., Pending, In Progress, Completed).
     */
    public void setStatus(String status){
        this.status = status;
    }
    /**
     * Sets a new due date for the task.
     * @param dueDate - The updated due date.
     */
    public void setDueDate(String dueDate){
        this.dueDate = dueDate;
    }
    /**
     * Sets a new priority level for the task.
     * @param priority - The updated priority (High, Medium, Low).
     */
    public void setPriority(String priority){
        this.priority = priority;
    }
     /**
     * Sets a new name/title for the task.
     * @param name - The updated name of the task.
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Marks the task as completed by updating the status and completion flag.
     */
    public void markAsCompleted(){
        this.completed = true;
        this.status = "Completed";
    }
    /**
     * @return true if the task has been completed, false otherwise.
     */
    public boolean isCompleted() {
        return completed;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Returns a string representation of the task details.
     * @return A formatted string displaying the task's ID, name, due date, priority, description and status.
     */
    @Override
    public String toString() {
        return "Task ID: " + id +
               ", Name: " + name +
               ", Due: " + dueDate +
               ", Priority: " + priority +
               ", Status: " + status +
               (description != null && !description.isEmpty() ? ", Desc: " + description : "");
    }

}
