package st10375267poe3;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class TaskManagement {
    static List<String> developers = new ArrayList<>();
    static List<String> taskNames = new ArrayList<>();
    static List<String> taskIDs = new ArrayList<>();
    static List<Integer> taskDurations = new ArrayList<>();
    static List<String> taskStatuses = new ArrayList<>();
    static ArrayList<Task> tasks = new ArrayList<>();
    static int totalHours = 0;
    static int taskCounter = 0;

    public static void addTask() {
        int taskCount = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks you wish to enter:"));
        for (int i = 0; i < taskCount; i++) {
            String taskNumber = String.format("%02d", taskCounter + i + 1);
            JOptionPane.showMessageDialog(null, "Task #" + taskNumber);
            String taskName = JOptionPane.showInputDialog("Enter the task name:");
            String taskDescription = JOptionPane.showInputDialog("Enter the task description (max 50 characters):");

            while (taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
                taskDescription = JOptionPane.showInputDialog("Enter the task description (max 50 characters):");
            }

            String developerFirstName = JOptionPane.showInputDialog("Enter the developer's first name:");
            String developerLastName = JOptionPane.showInputDialog("Enter the developer's last name:");
            
            String[] taskStatusOptions = {"To Do", "Done", "Doing"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, "Select task status:", "Task Status",
                    JOptionPane.PLAIN_MESSAGE, null, taskStatusOptions, taskStatusOptions[0]);
            
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter the task duration in hours:"));
                    
            Task task = new Task(taskName, taskDescription, developerFirstName, developerLastName, taskStatus, taskDuration);
            tasks.add(task);
            taskNames.add(taskName);
            taskIDs.add(task.createTaskID());
            taskDurations.add(taskDuration);
            taskStatuses.add(taskStatus);
            developers.add(developerFirstName + " " + developerLastName);
            totalHours += taskDuration;

            StringBuilder taskDetails = new StringBuilder();
            taskDetails.append("Task Status: ").append(taskStatus).append("\n");
            taskDetails.append("Developer Details: ").append(developerFirstName).append(" ").append(developerLastName).append("\n");
            taskDetails.append("Task Name: ").append(taskName).append("\n");
            taskDetails.append("Task Description: ").append(taskDescription).append("\n");
            taskDetails.append("Task ID: ").append(task.createTaskID()).append("\n");
            taskDetails.append("Duration: ").append(taskDuration).append(" hours");

            JOptionPane.showMessageDialog(null, taskDetails.toString());
        }
    }

    public static void showReport() {
        if (taskNames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks found. Please add tasks first.");
            return;
        }

        String[] reportOptions = { "Tasks with the status of Done", "Tasks with the longest Duration",
                "Search for Task (Task Name)", "Tasks Assigned to Developer (Developer Name)", "Delete a task (Task Name)",
                "Show full Report" };

        String reportChoice = (String) JOptionPane.showInputDialog(null, "Select a report option:", "Report",
                JOptionPane.PLAIN_MESSAGE, null, reportOptions, reportOptions[0]);

        switch (reportChoice) {
            case "Tasks with the status of Done" -> showTasksWithStatus("Done");
            case "Tasks with the longest Duration" -> showTaskWithLongestDuration();
            case "Search for Task (Task Name)" -> searchForTaskByName();
            case "Tasks Assigned to Developer (Developer Name)" -> searchTasksByDeveloper();
            case "Delete a task (Task Name)" -> deleteTask();
            case "Show full Report" -> showFullReport();
            default -> JOptionPane.showMessageDialog(null, "Invalid report option. Please try again.");
        }
    }

    public static void showTasksWithStatus(String status) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Tasks with the status of ").append(status).append(":\n");

        for (int i = 0; i < taskNames.size(); i++) {
            if (taskStatuses.get(i).equalsIgnoreCase(status)) {
                reportBuilder.append("Developer: ").append(developers.get(i)).append("\n");
                reportBuilder.append("Task Name: ").append(taskNames.get(i)).append("\n");
                reportBuilder.append("Task Duration: ").append(taskDurations.get(i)).append(" hours\n");
                reportBuilder.append("-------------------\n");
            }
        }

        JOptionPane.showMessageDialog(null, reportBuilder.toString());
    }

    public static void showTaskWithLongestDuration() {
        int maxDuration = 0;
        int maxDurationIndex = -1;

        for (int i = 0; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > maxDuration) {
                maxDuration = taskDurations.get(i);
                maxDurationIndex = i;
            }
        }

        if (maxDurationIndex != -1) {
            JOptionPane.showMessageDialog(null,
                    "Task with the longest duration:\nDeveloper: " + developers.get(maxDurationIndex) + "\nTask Duration: "
                            + taskDurations.get(maxDurationIndex) + " hours");
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found.");
        }
    }

    public static void searchForTaskByName() {
        String taskName = JOptionPane.showInputDialog("Enter the task name:");

        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                JOptionPane.showMessageDialog(null,
                        "Task Name: " + taskNames.get(i) + "\nDeveloper: " + developers.get(i) + "\nTask Status: "                + taskStatuses.get(i));
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    public static void searchTasksByDeveloper() {
        String developerName = JOptionPane.showInputDialog("Enter the developer's First and Last name:");
        boolean foundTasks = false;

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Tasks assigned to ").append(developerName).append(":\n");

        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).equalsIgnoreCase(developerName)) {
                foundTasks = true;
                reportBuilder.append("Task Name: ").append(taskNames.get(i)).append("\n");
                reportBuilder.append("Task Status: ").append(taskStatuses.get(i)).append("\n");
                reportBuilder.append("-------------------\n");
            }
        }

        if (foundTasks) {
            JOptionPane.showMessageDialog(null, reportBuilder.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found for the developer.");
        }
    }

    public static void deleteTask() {
        String taskName = JOptionPane.showInputDialog("Enter the task name to delete:");

        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equalsIgnoreCase(taskName)) {
                taskNames.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                developers.remove(i);
                JOptionPane.showMessageDialog(null, "Task deleted successfully.");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    public static void showFullReport() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks found. Please add tasks first.");
            return;
        }

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Full Task Report:\n");

        for (Task task : tasks) {
            reportBuilder.append(task.printTaskDetails());
            reportBuilder.append("-------------------\n");
        }

        JOptionPane.showMessageDialog(null, reportBuilder.toString());
    }
}
