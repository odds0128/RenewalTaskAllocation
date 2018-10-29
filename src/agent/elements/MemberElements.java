package agent.elements;

import agent.Agent;
import task.Subtask;
import static root.EnvironmentalConstants.*;

public class MemberElements  {
    private double  e_member;
    private Subtask assignedSubtask;
    private double  DE_m;
    private Agent leader;

    MemberElements(){
        this.e_member = INITIAL_E_MEMBER;
        this.DE_m = INITIAL_E_MEMBER;  // 他の全エージェントについて繰り返す
    }

}
