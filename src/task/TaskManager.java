package task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static TaskManager _singleton_ = new TaskManager();
    public  static TaskManager getInstance(){
        return _singleton_;
    }

    private List<Task> taskQueue;

    private TaskManager(){
        taskQueue = new ArrayList<>();
        System.out.println("TaskManager generated.");
    }


    public void vInitiateTaskQueue(int task_num){
        for( int i = 0; i < task_num; i++ ){
            taskQueue.add(new Task());
        }
    }

    public static void vReset(){

    }
}
