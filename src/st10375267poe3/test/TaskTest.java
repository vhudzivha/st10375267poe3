package st10375267poe3.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import st10375267poe3.Task;

public class TaskTest {

    private Task task;

    @BeforeEach
    public void setup() {
        task = new Task("Create Login", "Task to create login", "Mike", "Smith", "To Do", 5);
    }

    @Test
    public void testCreateTaskID() {
        assertEquals("TaskID", task.createTaskID());
    }

    @Test
    public void testPrintTaskDetails() {
        String expectedDetails = "Task Name: Create Login\n" +
                "Task Description: Task to create login\n" +
                "Developer: Mike Smith\n" +
                "Task Status: To Do\n" +
                "Task Duration: 5 hours\n";
        assertEquals(expectedDetails, task.printTaskDetails());
    }
}
