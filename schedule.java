import java.util.*;

class Task {
    String description;
    String startTime;
    String endTime;
    String priority;

    public Task(String description, String startTime, String endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    // Getters and other necessary methods
}

class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;

    private ScheduleManager() {
        tasks = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        for (Task t : tasks) {
            if (conflicts(task, t)) {
                System.out.println("Error: Task conflicts with existing task " + t.description);
                return;
            }
        }
        tasks.add(task);
        System.out.println("Task added successfully. No conflicts.");
    }

    public void removeTask(String description) {
        tasks.removeIf(t -> t.description.equals(description));
        System.out.println("Task removed successfully.");
    }

    public void viewTasks() {
        tasks.sort(Comparator.comparing(t -> t.startTime));
        for (Task task : tasks) {
            System.out.println(task.startTime + " - " + task.endTime + ": " + task.description + " [" + task.priority + "]");
        }
    }

    private boolean conflicts(Task newTask, Task existingTask) {
        // Check for time conflicts
        return !(newTask.endTime.compareTo(existingTask.startTime) <= 0 || newTask.startTime.compareTo(existingTask.endTime) >= 0);
    }
}

class Main {
    public static void main(String[] args) {
        ScheduleManager manager = ScheduleManager.getInstance();
        manager.addTask(new Task("Morning Exercise", "07:00", "08:00", "High"));
        manager.addTask(new Task("Team Meeting", "09:00", "10:00", "Medium"));
        manager.viewTasks();
        manager.removeTask("Morning Exercise");
        manager.viewTasks();
    }
}
