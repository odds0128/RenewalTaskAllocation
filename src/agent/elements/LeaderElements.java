package agent.elements;

import agent.Agent;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.List;

import static root.EnvironmentalConstants.INITIAL_E_MEMBER;

public class LeaderElements {
    private double    e_leader;
    private double[]  DE_l;

    private Task assignedTask;
    private List<Agent> candidates;
    private List<Agent> teammates;

    public LeaderElements(int agent_num){
        this.DE_l = new double[agent_num];
        this.candidates = new ArrayList<>();
        this.teammates  = new ArrayList<>();

        this.e_leader = INITIAL_E_MEMBER;
        for(int i=0; i< agent_num; i++) {
            this.DE_l[i] = INITIAL_E_MEMBER;
        }
    }

    public double getE_leader() {
        return e_leader;
    }

    public void setE_leader(double e_leader) {
        this.e_leader = e_leader;
    }

    public double[] getDE_l() {
        return DE_l;
    }

    public void setDE_l(double[] DE_l) {
        this.DE_l = DE_l;
    }

    public Task getAssignedTask() {
        return assignedTask;
    }

    public void setAssignedTask(Task assignedTask) {
        this.assignedTask = assignedTask;
    }

    public List<Agent> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Agent> candidates) {
        this.candidates = candidates;
    }

    public List<Agent> getTeammates() {
        return teammates;
    }

    public void setTeammates(List<Agent> teammates) {
        this.teammates = teammates;
    }

}
