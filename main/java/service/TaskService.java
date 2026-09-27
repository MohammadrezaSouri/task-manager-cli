package service;

import jakarta.xml.bind.JAXBException;
import model.Task;
import storage.XmlTaskStorage;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private TaskService(){}
    private final static TaskService TASK_SERVICE = new TaskService();
    public static TaskService getTaskService(){
        return TASK_SERVICE;
    }

    private List<Task> tasks = new ArrayList<>();
    private final static String FILE_PATH = "tasks.xml";
    private int nextId = 1;

    public void addTask(String title, String description, Task.PRIORITY priority, Task.CATEGORY category, LocalDate deadline){
        Task task = new Task(nextId++, title, description, LocalDateTime.now(), deadline, priority, category, Task.STATUS.PENDING);
        tasks.add(task);
    }
    public void updateStatus(int id, Task.STATUS status){
        for (Task task : tasks){
            if (task.getId() == id){
                task.setStatus(status);
                return;
            }
        }
    }
    public void deleteTask(int id){
        tasks.removeIf(task -> task.getId() == id);
    }
    public List<Task> getAllTasks(){
        return tasks;
    }
    public void save() throws JAXBException {
        XmlTaskStorage.saveTasks(tasks, FILE_PATH);
    }

    public void load() throws JAXBException{
        File file = new File(FILE_PATH);
        if (file.exists()){
            tasks = XmlTaskStorage.loadTasks(FILE_PATH);
        }
    }

}
