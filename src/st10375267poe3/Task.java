package st10375267poe3;

public class Task {
    private final String taskName;
    private final String taskDescription;
    private final String developerFirstName;
    private final String developerLastName;
    private final String taskStatus;
    private final int taskDuration;

    public Task(String taskName, String taskDescription, String developerFirstName, String developerLastName,
                String taskStatus, int taskDuration) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerFirstName = developerFirstName;
        this.developerLastName = developerLastName;
        this.taskStatus = taskStatus;
        this.taskDuration = taskDuration;
    }

    public String createTaskID() {
        // Implement logic for creating task ID
        return "TaskID"; 
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getDeveloperFirstName() {
        return developerFirstName;
    }

    public String getDeveloperLastName() {
        return developerLastName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public String printTaskDetails() {
        StringBuilder taskDetails = new StringBuilder();
        taskDetails.append("Task Name: ").append(taskName).append("\n");
        taskDetails.append("Task Description: ").append(taskDescription).append("\n");
        taskDetails.append("Developer: ").append(developerFirstName).append(" ").append(developerLastName).append("\n");
        taskDetails.append("Task Status: ").append(taskStatus).append("\n");
        taskDetails.append("Task Duration: ").append(taskDuration).append(" hours\n");

        return taskDetails.toString();
    }
}
