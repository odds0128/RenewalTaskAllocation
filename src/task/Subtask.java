package task;

import static root.EnvironmentalConstants.*;
import static root.myRandom.getRandomInt;

public class Subtask {
    private static int _subtask_id_ = 0;

    private int   id;
    private int   requiredType;
    private int[] requiredResources;

    Subtask(){
        this.id = _subtask_id_++;
        this.requiredResources = aSetResources();
    }

    /**
     * 副作用でrequiredTypeを設定していることに注意
     */
    private int[] aSetResources() {
        int[] temp = new int[RESOURCE_TYPES];

        requiredType = getRandomInt(0, RESOURCE_TYPES-1);
        temp[requiredType] = getRandomInt(TASK_MIN_RESOURCE, TASK_MAX_RESOURCE);

        return temp;
    }

    public int getRequiredType() {
        return requiredType;
    }

    public int[] getRequiredResources() {
        return requiredResources;
    }

    public static void vResetSubtask(){
        _subtask_id_ = 0;
    }

    @Override
    public String toString(){
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append("id: " + this.id);

        sb.append(", resources: " );
        for( int i = 0; i < RESOURCE_TYPES; i++ ){
            sb.append(requiredResources[i] + ", ");
        }
        return sb.toString();
    }
}
