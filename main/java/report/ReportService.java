package report;

import model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ReportService {
    public List<Task> getTodayTasks(List<Task> tasks){
        return tasks.stream()
                .filter(task -> task.getDeadline() != null
                && task.getDeadline().equals(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public List<Task> getOverdueTasks(List<Task> tasks){
        return tasks.stream()
                .filter(task -> task.getDeadline() != null
                        && task.getDeadline().isBefore(LocalDate.now())
                        && task.getStatus() != Task.STATUS.COMPLETED)
                .collect(Collectors.toList());
    }

    public List<Task> getCompletedTasks(List<Task> tasks){
        return tasks.stream()
                .filter(task -> task.getStatus() == Task.STATUS.COMPLETED)
                .collect(Collectors.toList());
    }
}
