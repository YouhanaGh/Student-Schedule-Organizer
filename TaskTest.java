import org.junit.Assert;
import org.junit.jupiter.api.Test;


class TaskTest {

    @Test
    void testTaskCreation() {
        Task task = new Task("Study for Exam", "2025-03-14", "High", "Upcoming Exam");
        Assert.assertEquals("Study for Exam", task.getName());
        Assert.assertEquals("2025-03-14", task.getDueDate());
        Assert.assertEquals("High", task.getPriority());
        Assert.assertEquals("Pending", task.getStatus());
        Assert.assertEquals("Upcoming Exam", task.getDescription());
        Assert.assertFalse(task.isCompleted());
    }

    @Test
    void testMarkAsCompleted() {
        Task task = new Task("Finish Assignment", "2025-03-14", "Medium", "Assignment is due");
        task.markAsCompleted();
        Assert.assertTrue(task.isCompleted());
        Assert.assertEquals("Completed", task.getStatus());
    }
    
    @Test
    void testTaskIdAutoIncrement() {
        Task task1 = new Task("Study for Exam", "2025-03-14", "High", "Upcoming Exam");
        Task task2 = new Task("Finish Assignment", "2025-03-14", "Medium", "Assignment is due");
        Assert.assertEquals(task1.getId() + 1, task2.getId());
    }

    @Test
    void testSetDueDate() {
        Task task1 = new Task("Study for Exam", "2025-03-14", "High", "Upcoming Exam");
        task1.setDueDate("2025-03-14");
        Assert.assertEquals("2025-03-14", task1.getDueDate());
    }

    @Test
    void testSetPriority() {
        Task task2 = new Task("Finish Assignment", "2025-03-14", "Medium", "Assignment is due");
        task2.setPriority("High");
        Assert.assertEquals("High", task2.getPriority());
    }

    @Test
    void testSetStatus() {
        Task task2 = new Task("Finish Assignment", "2025-03-14", "Medium", "Assignment is due");
        task2.setStatus("In Progress");
        Assert.assertEquals("In Progress", task2.getStatus());
    }
    @Test
    void testSetName() {
        Task task2 = new Task("Finish Assignment", "2025-03-14", "Medium", "Assignment is due");
        task2.setName("Finish Assignment2");
        Assert.assertEquals("Finish Assignment2", task2.getName());
    }
    @Test
    void testSetDescription() {
        Task task2 = new Task("Finish Assignment", "2025-03-14", "Medium", "Assignment is due");
        task2.setDescription("Assignment is due soon");
        Assert.assertEquals("Assignment is due soon", task2.getDescription());
    }
}
