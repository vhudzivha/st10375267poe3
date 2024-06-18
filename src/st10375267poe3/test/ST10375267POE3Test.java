package st10375267poe3.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import st10375267poe3.ST10375267POE3;
import st10375267poe3.Task;

import java.util.ArrayList;
import java.util.List;

public class ST10375267POE3Test {
    private List<Task> testTasks;

    @BeforeEach
    public void setup() {
        ST10375267POE3.tasks = new ArrayList<>();
        ST10375267POE3.taskNames = new ArrayList<>();
        ST10375267POE3.taskIDs = new ArrayList<>();
        ST10375267POE3.taskDurations = new ArrayList<>();
        ST10375267POE3.taskStatuses = new ArrayList<>();
        ST10375267POE3.developers = new ArrayList<>();
        ST10375267POE3.totalHours = 0;
        
        Task task1 = new Task("Create Login", "Task to create login", "Mike", "Smith", "To Do", 5);
        Task task2 = new Task("Create Add Features", "Task to add features", "Edward", "Harrison", "Doing", 8);
        Task task3 = new Task("Create Reports", "Task to create reports", "Samantha", "Paulson", "Done", 2);
        Task task4 = new Task("Add Arrays", "Task to add arrays", "Glenda", "Oberholzer", "To Do", 11);
        
        ST10375267POE3.tasks.add(task1);
        ST10375267POE3.tasks.add(task2);
        ST10375267POE3.tasks.add(task3);
        ST10375267POE3.tasks.add(task4);
        
        ST10375267POE3.taskNames.add(task1.getTaskName());
        ST10375267POE3.taskNames.add(task2.getTaskName());
        ST10375267POE3.taskNames.add(task3.getTaskName());
        ST10375267POE3.taskNames.add(task4.getTaskName());
        
        ST10375267POE3.taskIDs.add(task1.createTaskID());
        ST10375267POE3.taskIDs.add(task2.createTaskID());
        ST10375267POE3.taskIDs.add(task3.createTaskID());
        ST10375267POE3.taskIDs.add(task4.createTaskID());
        
        ST10375267POE3.taskDurations.add(task1.getTaskDuration());
        ST10375267POE3.taskDurations.add(task2.getTaskDuration());
        ST10375267POE3.taskDurations.add(task3.getTaskDuration());
        ST10375267POE3.taskDurations.add(task4.getTaskDuration());
        
        ST10375267POE3.taskStatuses.add(task1.getTaskStatus());
        ST10375267POE3.taskStatuses.add(task2.getTaskStatus());
        ST10375267POE3.taskStatuses.add(task3.getTaskStatus());
        ST10375267POE3.taskStatuses.add(task4.getTaskStatus());
        
        ST10375267POE3.developers.add(task1.getDeveloperFirstName() + " " + task1.getDeveloperLastName());
        ST10375267POE3.developers.add(task2.getDeveloperFirstName() + " " + task2.getDeveloperLastName());
        ST10375267POE3.developers.add(task3.getDeveloperFirstName() + " " + task3.getDeveloperLastName());
        ST10375267POE3.developers.add(task4.getDeveloperFirstName() + " " + task4.getDeveloperLastName());
        
        ST10375267POE3.totalHours = 26; // 5 + 8 + 2 + 11
    }

    @Test
    public void testDeveloperArrayCorrectlyPopulated() {
        String[] expectedDevelopers = {"Mike Smith", "Edward Harrison", "Samantha Paulson", "Glenda Oberholzer"};
        String[] actualDevelopers = ST10375267POE3.developers.toArray(new String[0]);
        assertArrayEquals(expectedDevelopers, actualDevelopers);
    }

    @Test
    public void testDisplayDeveloperAndDurationForLongestTask() {
        int maxDuration = 0;
        int maxDurationIndex = -1;

        for (int i = 0; i < ST10375267POE3.taskDurations.size(); i++) {
            if (ST10375267POE3.taskDurations.get(i) > maxDuration) {
                maxDuration = ST10375267POE3.taskDurations.get(i);
                maxDurationIndex = i;
            }
        }

        assertEquals(11, maxDuration);
        assertEquals("Glenda Oberholzer", ST10375267POE3.developers.get(maxDurationIndex));
    }

    @Test
    public void testSearchForTaskByName() {
        String taskName = "Create Login";
        boolean found = false;

        for (int i = 0; i < ST10375267POE3.taskNames.size(); i++) {
            if (ST10375267POE3.taskNames.get(i).equalsIgnoreCase(taskName)) {
                assertEquals("Mike Smith, Create Login", ST10375267POE3.developers.get(i) + ", " + ST10375267POE3.taskNames.get(i));
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    public void testSearchTasksByDeveloper() {
        String developerName = "Samantha Paulson";
        List<String> tasksByDeveloper = new ArrayList<>();

        for (int i = 0; i < ST10375267POE3.developers.size(); i++) {
            if (ST10375267POE3.developers.get(i).equalsIgnoreCase(developerName)) {
                tasksByDeveloper.add(ST10375267POE3.taskNames.get(i));
            }
        }

        assertEquals(1, tasksByDeveloper.size());
        assertEquals("Create Reports", tasksByDeveloper.get(0));
    }

    @Test
    public void testDeleteTask() {
        String taskName = "Create Reports";
        boolean isDeleted = false;

        for (int i = 0; i < ST10375267POE3.taskNames.size(); i++) {
            if (ST10375267POE3.taskNames.get(i).equalsIgnoreCase(taskName)) {
                ST10375267POE3.taskNames.remove(i);
                ST10375267POE3.taskIDs.remove(i);
                ST10375267POE3.taskDurations.remove(i);
                ST10375267POE3.taskStatuses.remove(i);
                ST10375267POE3.developers.remove(i);
                isDeleted = true;
                break;
            }
        }

        assertTrue(isDeleted);
        assertFalse(ST10375267POE3.taskNames.contains(taskName));
    }

    @Test
    public void testDisplayReport() {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Full Task Report:\n");

        for (Task task : ST10375267POE3.tasks) {
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
