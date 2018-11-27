package task;

import java.util.ArrayList;
import java.util.List;

import static root.EnvironmentalConstants.*;

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


    /**
     * 引数の分だけtaskqueueにtaskを生成する
     * 引数がtaskqueueの大きさを超えていた場合taskqueueの最大長分だけ生成する
     * @param task_num
     */
    //Verify: エラーを吐くべきか察してキュー一杯にタスクを生成すべきか
    public void vInitiateTaskQueue(int task_num){
        int temp = 0;
        if( task_num > MAX_TASKQUEUE_SIZE ){
            temp = MAX_TASKQUEUE_SIZE;
        }else{
            temp = task_num;
        }
        for( int i = 0; i < temp; i++ ){
            taskQueue.add(new Task());
        }
    }

    public Task tPopTask(){
        if( this.taskQueue.size() == 0 ){
            return null;
        }
        return this.taskQueue.remove(0);
    }

    public static void vReset(){

    }
}
