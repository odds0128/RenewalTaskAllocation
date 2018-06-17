package agent;

import java.util.ArrayList;
import java.util.List;

import myUtil.myRandom;
import static constants.EnvironmentalConstants.*;

public class Agent {
    private static int _agent_id;

    int id;
    int x, y;
    Roles role;
    int[] resources;


    public Agent(int x, int y) {
        this.id = _agent_id++;
        this.x = x;
        this.y = y;
        this.role = Roles.LEADER;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

}
