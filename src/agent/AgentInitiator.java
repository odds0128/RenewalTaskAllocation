package agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static root.EnvironmentalConstants.*;
import static root.myRandom.getRandomInt;

public class AgentInitiator {
    private static AgentInitiator _singleton_ = new AgentInitiator();
    public static AgentInitiator getInstance(){
        return _singleton_;
    }


    /**
     * 引数の数だけエージェントを生成する
     */
    List<Agent> lGenerateAgents(int agent_num, int leader_num) {
        List<Agent> agentList = new ArrayList<>();
        for (int i = 0; i < agent_num; i++) {
            agentList.add(new Agent());
        }
        agentList = lSetRoles(agentList, leader_num);

        return agentList;
    }

    /**
     * 場所が被らないように引数のエージェントに座標を設定する．
     * この時Agentのcoordinateにも値を入れてしまう
     */
    HashMap<Agent, Coordinate> hSetPositions(List<Agent> agents) {
        boolean[][] grid = new boolean[MAX_X][MAX_Y];  // 該当の格子点にエージェントがいたらtrue, いなければfalse
        HashMap<Agent, Coordinate> coordinates = new HashMap<>();

        // 座標を位置が被らないようにランダムに配置する
        int x, y;
        for (Agent ag : agents) {
            while (true) {
                x = getRandomInt(0, MAX_X - 1);
                y = getRandomInt(0, MAX_Y - 1);

                if ( !grid[x][y] ) {
                    grid[x][y] = true;
                    coordinates.put(ag, new Coordinate(x, y));

                    break;
                }
            }
        }
        return coordinates;
    }


    /**
     * エージェント間の遅延時間を設定するメソッド．
     * iからjへの通信とjからiへの通信は同じ時間がかかるという前提．
     * なお，i == jの時は0が入っている．
     */
    int[][] aSetDelays(HashMap<Agent, Coordinate> coordinates, List<Agent> agents) {
        int[][] delays_ = new int[AGENT_NUM][AGENT_NUM];
        int _size = agents.size();
        Agent from, to;

        for (int iFrom = 0; iFrom < _size; iFrom++) {
            from = agents.get(iFrom);
            for (int iTo = iFrom + 1; iTo < _size; iTo++) {
                to = agents.get(iTo);
                // 自分から相手への通信と相手から自分への通信は同じ時間
                delays_[iFrom][iTo] = iCalcDelay(coordinates.get(from), coordinates.get(to));
                delays_[iTo][iFrom] = iCalcDelay(coordinates.get(from), coordinates.get(to));
            }
        }
        return delays_;
    }

    /**
     * エージェント間のマンハッタン距離を計算し，returnするメソッド
     * □□□
     * □■□
     * □□□
     * このように座標を拡張し，真ん中からの距離を計算，その最短距離をとることで
     * トーラス構造の距離関係を割り出す
     * @return from - to 間の通信時間
     */
    private int iCalcDelay(Coordinate from, Coordinate to) {
        int _tillEnd = MAX_X / 2 + MAX_Y / 2;
        int _minDistance = Integer.MAX_VALUE;
        int _tilesX = 3;
        int _tilesY = 3;

        int fromX = from.get_x();
        int fromY = from.get_y();

        for (int i = 0; i < _tilesX; i++) {
            int toX = to.get_x() + (i - 1) * MAX_X;

            for (int j = 0; j < _tilesY; j++) {
                int toY = to.get_y() + (j - 1) * MAX_Y;
                int tempDistance = Math.abs(fromX - toX) + Math.abs(fromY - toY);

                if (tempDistance < _minDistance) {
                    _minDistance = tempDistance;
                }
            }
        }
        return (int) Math.ceil((double) _minDistance / _tillEnd * MAX_DELAY);
    }


    /**
     * 役割の初期化
     * _leader_numだけリーダーを設定する
     * FIXME: 書き方めちゃめちゃ下手くそなのでどうにかして
     */
    private List<Agent> lSetRoles(List<Agent> agents, int leader_num){
        List<Agent> newAgentsList = new ArrayList<>();
        int newLeaderIndex;
        Agent newLeader;
        int restAgentsNum = AGENT_NUM;

        assert leader_num < restAgentsNum : "leaders are more than all agents";

        for( int i = 0; i < leader_num; i++ ){
            newLeaderIndex = getRandomInt(0, restAgentsNum - 1);
            newLeader = agents.remove(newLeaderIndex);
            newLeader.roleName = RoleName.leader;
            newAgentsList.add(newLeader);
            restAgentsNum--;
        }

        newAgentsList.addAll(agents);
        return newAgentsList;
    }

    public void vReset(){

    }

}
