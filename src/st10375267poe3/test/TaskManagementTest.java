package st10375267poe3.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import st10375267poe3.Task;
import st10375267poe3.TaskManagement;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementTest {
    private static List<Task> testTasks;

    @BeforeEach
    public void setup() {
        testTasks = new ArrayList<>();
        testTasks.add(new Task("Create Login", "Task to create login", "Mike", "Smith", "To Do", 5));
        testTasks.add(new Task("Create Add Features", "Task to add features", "Edward", "Harrison", "Doing", 8));
        testTasks.add(new Task("Create Reports", "Task to create reports", "Samantha", "Paulson", "Done", 2));
        testTasks.add(new Task("Add Arrays", "Task to add arrays", "Glenda", "Oberholzer", "To Do", 11));
    }

    @Test
    public void testDeveloperArrayCorrectlyPopulated() {
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"};
        String[] actualDevelopers = testTasks.stream().map(t -> t.getDeveloperFirstName() + " " + t.getDeveloperLastName()).toArray(String[]::new);
        assertArrayEquals(expectedDevelopers, actualDevelopers);
    }

    @Test
    public void testDisplayDeveloperAndDurationForLongestTask() {
        Task longestTask = testTasks.stream().max((t1, t2) -> Integer.compare(t1.getTaskDuration(), t2.getTaskDuration())).orElse(null);
        assertNotNull(longestTask);
        assertEquals("Glenda Oberholzer, 11", longestTask.getDeveloperFirstName() + " " + longestTask.getDeveloperLastName() + ", " + longestTask.getTaskDuration());
    }

    @Test
    public void testSearchForTaskByName() {
        String taskName = "Create Login";
        Task task = testTasks.stream().filter(t -> t.getTaskName().equalsIgnoreCase(taskName)).findFirst().orElse(null);
        assertNotNull(task);
        assertEquals("Mike Smith, Create Login", task.getDeveloperFirstName() + " " + task.getDeveloperLastName() + ", " + task.getTaskName());
    }

    @Test
    public void testSearchTasksByDeveloper() {
        String developerName = "Samantha Paulson";
        List<Task> tasksByDeveloper = new ArrayList<>();
        for (Task task : testTasks) {
            if ((task.getDeveloperFirstName() + " " + task.getDeveloperLastName()).equalsIgnoreCase(developerName)) {
                tasksByDeveloper.add(task);
            }
        }
        assertEquals(1, tasksByDeveloper.size());
        assertEquals("Create Reports", tasksByDeveloper.get(0).getTaskName());
    }

    @Test
    public void testDeleteTask() {
        String taskName = "Create Reports";
        boolean isDeleted = testTasks.removeIf(t -> t.getTaskName().equalsIgnoreCase(taskName));
        assertTrue(isDeleted);
        assertFalse(testTasks.stream().anyMatch(t -> t.getTaskName().equalsIgnoreCase(taskName)));
    }

    @Test
    public void testDisplayReport() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Full Task Report:\n");

        for (Task task : testTasks) {
            reportBuilder.append(task.printTaskDetails());
            reportBuilder.append("-------------------\n");
        }

        String expectedReport = "Full Task Report:\n" +
                "Task Name: Create Login\n" +
                "Task Description: Task to create login\n" +
                "Developer: Mike Smith\n" +
                "Task Status: To Do\n" +
                "Task Duration: 5 hours\n" +
                "-------------------\n" +
                "Task Name: Create Add Features\n" +
                "Task Description: Task to add features\n" +
                "Developer: Edward Harrison\n" +
                "Task Status: Doing\n" +
                "Task Duration: 8 hours\n" +
                "-------------------\n" +
                "Task Name: Create Reports\n" +
                "Task Description: Task to create reports\n" +
                "Developer: Samantha Paulson\n" +
                "Task Status: Done\n" +
                "Task Duration: 2 hours\n" +
                "-------------------\n" +
                "Task Name: Add Arrays\n" +
                "Task Description: Task to add arrays\n" +
                "Developer: Glenda Oberholzer\n" +
                "Task Status: To Do\n" +
                "Task Duration: 11 hours\n" +
                "-------------------\n";

        assertEquals(expectedReport, reportBuilder.toString());
    }
}
