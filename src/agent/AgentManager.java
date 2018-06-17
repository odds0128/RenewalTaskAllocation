package agent;

import agent.Agent;
import myUtil.myRandom;

import java.util.ArrayList;
import java.util.List;

import static constants.EnvironmentalConstants.*;


public class AgentManager {
    private static int _maxX = MAX_X, _maxY = MAX_Y;
    private static Agent[][] _grid = new Agent[_maxX][_maxY];


    public static List<Agent> initiateAgents(int agent_num){
        List<Agent> agents = lGenerateAgents(agent_num);
        setReliableAgent(agents);
        return agents;
    }

    private static List<Agent> lGenerateAgents(int agent_num) {
        List<Agent> agentList = new ArrayList<>();
        Agent[][] grid = getGrid();

        int x, y;

        // 座標をランダムに配置する
        for (int i = 0; i < agent_num; i++) {
            // 位置が被らないようにする
            while (true) {
                x = myRandom.getRandomInt(0, _maxX-1);
                y = myRandom.getRandomInt(0, _maxY-1);

                if( grid[x][y] == null ) {
                    break;
                }
            }
            Agent newAgent = new Agent(x, y);
            setAgentToGrid(x,y, newAgent);
            agentList.add(newAgent);
        }

        return agentList;
    }

    private static void setAgentToGrid(int x, int y, Agent newAgent){
        _grid[x][y] = newAgent;
    }

    // TODO: エージェント間の距離の実装
    private static int calcDelay(Agent from, Agent to){
        return 0;
    }

    // TODO: 信頼エージェントの実装
    public static void setReliableAgent(List<Agent> agents){

    }

    public static Agent[][] getGrid() {
        return _grid;
    }
}
