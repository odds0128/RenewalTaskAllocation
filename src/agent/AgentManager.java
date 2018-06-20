package agent;


import java.util.ArrayList;
import java.util.List;

import static constants.EnvironmentalConstants.*;
import static myUtil.myRandom.*;


public class AgentManager {
    static int[][] _delays_ = new int[AGENT_NUM][AGENT_NUM]; // 添字のエージェント間の通信遅延


    public static List<Agent> initiateAgents(int agent_num) {
        List<Agent> agents = lGenerateAgents(agent_num);
        setPositions(agents);
        setDelays(agents);
        setReliableAgents(agents);
        return agents;
    }

    private static List<Agent> lGenerateAgents(int agent_num) {
        List<Agent> agentList = new ArrayList<>();
        for (int i = 0; i < agent_num; i++) {
            agentList.add(new Agent());
        }
        return agentList;
    }

    private static void setPositions(List<Agent> agents) {
        boolean[][] grid = new boolean[MAX_X][MAX_Y];  // 該当の格子点にエージェントがいたらtrue, いなければfalse


        // 座標を位置が被らないようにランダムに配置する
        int x, y;
        for (Agent ag : agents) {
            while (true) {
                x = getRandomInt(0, MAX_X - 1);
                y = getRandomInt(0, MAX_Y - 1);

                if ( !grid[x][y] ) {
                    grid[x][y] = true;
                    break;
                }
            }
            ag.setPosition(new Position(x, y));
        }
    }


    // TODO: エージェント間の距離の実装
    private static void setDelays(List<Agent> agents) {
        int _size = agents.size();
        Agent from, to;

        for (int iFrom = 0; iFrom < _size; iFrom++) {
            from = agents.get(iFrom);
            for (int iTo = iFrom + 1; iTo < _size; iTo++) {
                to = agents.get(iTo);
                // 自分から相手への通信と相手から自分への通信は同じ時間
                 _delays_[iFrom][iTo] = calcDelay(from, to);
                 _delays_[iTo][iFrom] = calcDelay(from, to);
            }
        }
    }

    /**
     * calcDelayメソッド
     * エージェント間のマンハッタン距離を計算し，returnするメソッド
     * □□□
     * □■□
     * □□□
     * このように座標を拡張し，真ん中からの距離を計算，その最短距離をとることで
     * トーラス構造の距離関係を割り出す
     */
    private static int calcDelay(Agent from, Agent to) {
        int _tillEnd = MAX_X / 2 + MAX_Y / 2;
        int _minDistance = Integer.MAX_VALUE;
        int _tilesX = 3;
        int _tilesY = 3;

        int fromX = from.position.x;
        int fromY = from.position.y;

        for (int i = 0; i < _tilesX; i++) {
            int toX = to.position.x + (i - 1) * MAX_X;

            for (int j = 0; j < _tilesY; j++) {
                int toY = to.position.y + (j - 1) * MAX_Y;
                int tempDistance = Math.abs(fromX - toX) + Math.abs(fromY - toY);

                if (tempDistance < _minDistance) {
                    _minDistance = tempDistance;
                }
            }
        }
        return (int) Math.ceil((double) _minDistance / _tillEnd * MAX_DELAY);
    }

    // TODO: 信頼エージェントの実装
    private static void setReliableAgents(List<Agent> agents){

    }

}
