package app;

import jakarta.xml.bind.JAXBException;
import model.Task;
import report.ReportService;
import service.TaskService;

import java.time.LocalDate;
import java.util.List;

public class Menu {
    private final TaskService taskService = TaskService.getTaskService();
    private final ReportService reportService = new ReportService();

    public void start(){
        while (true){
            IO.println("--- TASK MANAGER ---");
            IO.println("1. Add Task");
            IO.println("2. View Tasks");
            IO.println("3. Update Task");
            IO.println("4. Delete Task");
            IO.println("5. Today's Tasks");
            IO.println("6. Overdue Tasks");
            IO.println("7. Complete Tasks");
            IO.println("8. Save");
            IO.println("9. Exit");
            IO.println("Choose an option: ");

            switch (IO.readln()){
                case "1" -> handleAddTask();
                case "2" -> handleViewAll();
                case "3" -> handleUpdateStatus();
                case "4" -> handleDelete();
                case "5" -> printTasks(reportService.getTodayTasks(taskService.getAllTasks()));
                case "6" -> printTasks(reportService.getOverdueTasks(taskService.getAllTasks()));
                case "7" -> printTasks(reportService.getCompletedTasks(taskService.getAllTasks()));
                case "8" -> handleSave();
                case "9" -> { return; }
                default -> IO.println("Invalid option.");
            }
        }
    }
    private void handleAddTask() {
        IO.println("Title: ");
        String title = IO.readln();
        IO.println("Description: ");
        String description = IO.readln();
        IO.println("Priority (LOW/MEDIUM/HIGH): ");
        Task.PRIORITY priority = Task.PRIORITY.valueOf(IO.readln().toUpperCase());
        IO.println("Category (WORK/PERSONAL/STUDY/OTHER): ");
        Task.CATEGORY category = Task.CATEGORY.valueOf(IO.readln().toUpperCase());
        IO.println("Deadline (YYYY-MM-DD): ");
        LocalDate deadline = LocalDate.parse(IO.readln());

        taskService.addTask(title, description, priority, category, deadline);
        IO.println("Task added.");
    }

    private void handleViewAll() {
        printTasks(taskService.getAllTasks());
    }

    private void handleUpdateStatus() {
        IO.println("Task ID: ");
        int id = Integer.parseInt(IO.readln());
        IO.println("New Status (PENDING/IN_PROGRESS/COMPLETED): ");
        Task.STATUS status = Task.STATUS.valueOf(IO.readln().toUpperCase());
        taskService.updateStatus(id, status);
        IO.println("Status updated.");
    }

    private void handleDelete() {
        IO.println("Task ID: ");
        int id = Integer.parseInt(IO.readln());
        taskService.deleteTask(id);
        IO.println("Task deleted.");
    }

    private void handleSave() {
        try {
            taskService.save();
            IO.println("Saved.");
        } catch (JAXBException e) {
            IO.println("Save failed: " + e.getMessage());
        }
    }

    private void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            IO.println("No tasks found.");
            return;
        }
        for (Task task : tasks) {
            IO.println("[" + task.getId() + "] " + task.getTitle() +
                    " | " + task.getPriority() +
                    " | " + task.getStatus() +
                    " | Deadline: " + task.getDeadline());
        }
    }
}
