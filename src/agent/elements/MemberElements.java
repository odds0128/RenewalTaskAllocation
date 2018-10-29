package agent.elements;

import agent.Agent;
import task.Subtask;

import java.util.ArrayList;
import java.util.List;

import static root.EnvironmentalConstants.*;

public class MemberElements  {
    private double    e_member;
    private double[]  DE_m;

    private List<Subtask> assignedSubtask;
    private Agent leader;

    MemberElements(int agent_num){
        this.DE_m = new double[agent_num];
        this.assignedSubtask = new ArrayList<>();

        this.e_member = INITIAL_E_MEMBER;
        for(int i=0; i< agent_num; i++) {
            this.DE_m[i] = INITIAL_E_MEMBER;  // 他の全エージェントについて繰り返す
        }
    }


    public double getE_member() {
        return e_member;
    }

    public void setE_member(double e_member) {
        this.e_member = e_member;
    }

    public double[] getDE_m() {
        return DE_m;
    }

    public void setDE_m(double[] DE_m) {
        this.DE_m = DE_m;
    }

    public Agent getLeader() {
        return leader;
    }

    public void setLeader(Agent leader) {
        this.leader = leader;
    }

    public List<Subtask> getAssignedSubtask() {
        return assignedSubtask;
    }

    public void setAssignedSubtask(List<Subtask> assignedSubtask) {
        this.assignedSubtask = assignedSubtask;
    }
}
