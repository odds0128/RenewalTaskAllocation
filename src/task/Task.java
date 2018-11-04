package task;

import java.util.ArrayList;
import java.util.List;

import root.myRandom;
import static root.EnvironmentalConstants.*;

public class Task {
    private static int _task_id_ = 0;

    private int id;
    private List<Subtask> subtasks;

    Task(){
        this.id = _task_id_++;
        this.subtasks = new ArrayList<>();
        int st_num = myRandom.getRandomInt(MIN_SUBTASKS, MAX_SUBTASKS);

        for( int i = 0; i < st_num; i++ ){
            subtasks.add(new Subtask());
        }
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    static void vResetTask(){
        _task_id_ = 0;
    }
}
