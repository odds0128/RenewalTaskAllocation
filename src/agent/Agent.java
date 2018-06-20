package agent;

import java.util.ArrayList;
import java.util.List;

import myUtil.myRandom;
import static constants.EnvironmentalConstants.*;

public class Agent {
    private static int _agent_id_;

    int id;
    Position position;
    Roles role;
    int[] resources;


    public Agent() {
        this.id = _agent_id_++;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
