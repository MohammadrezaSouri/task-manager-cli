package app;

import service.TaskService;

public class Main {
    static void main() {
        try {
            TaskService.getTaskService().load();
            new Menu().start();
        }catch (Exception e){
            e.printStackTrace();
            IO.println("Error");
        }
    }
}
