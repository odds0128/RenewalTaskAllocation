package task;

import java.util.ArrayList;
import java.util.List;

import root.myRandom;
import static root.EnvironmentalConstants.*;

public class Task {
    private List<Subtask> subtasks;

    Task(){
        this.subtasks = new ArrayList<>();
        int st_num = myRandom.getRandomInt(MIN_SUBTASKS, MAX_SUBTASKS);
        for( int i = 0; i < st_num; i++ ){
            subtasks.add(new Subtask());
        }
    }
}
