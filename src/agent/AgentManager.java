package agent;


import java.util.ArrayList;
import java.util.List;

import static constants.EnvironmentalConstants.*;
import static myUtil.myRandom.*;


public class AgentManager {
    static int[][] _delays_ = new int[AGENT_NUM][AGENT_NUM]; // 添字のエージェント間の通信遅延


    /**
     * 引数の数だけエージェントを生成し，
     * 座標・遅延時間を設定し，
     * 信頼エージェント・役割の初期値を入れる．
     * @param agent_num
     * @return
     */
    public static List<Agent> lInitiateAgents(int agent_num, int leader_num) {
        List<Agent> agents = lGenerateAgents(agent_num);
        vSetUpPositions(agents);
        vSetUpDelays(agents);
        vSetUpReliableAgents(agents);
        vSetUpRoles(agents, leader_num);
        return agents;
    }

    /**
     * 引数の数だけエージェントを生成する
     * @param _agent_num
     * @return
     */
    private static List<Agent> lGenerateAgents(int _agent_num) {
        List<Agent> agentList = new ArrayList<>();
        for (int i = 0; i < _agent_num; i++) {
            agentList.add(new Agent());
        }
        return agentList;
    }


    /**
     * 場所が被らないように引数のエージェントに座標を設定する．
     * @param agents
     */
    private static void vSetUpPositions(List<Agent> agents) {
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


    /**
     * エージェント間の遅延時間を設定するメソッド．
     * iからjへの通信とjからiへの通信は同じ時間がかかるという前提．
     * なお，i == jの時は0が入っている．
     * @param agents
     */
    private static void vSetUpDelays(List<Agent> agents) {
        int _size = agents.size();
        Agent from, to;

        for (int iFrom = 0; iFrom < _size; iFrom++) {
            from = agents.get(iFrom);
            for (int iTo = iFrom + 1; iTo < _size; iTo++) {
                to = agents.get(iTo);
                // 自分から相手への通信と相手から自分への通信は同じ時間
                 _delays_[iFrom][iTo] = iCalcDelay(from, to);
                 _delays_[iTo][iFrom] = iCalcDelay(from, to);
            }
        }
    }

    /**
     * エージェント間のマンハッタン距離を計算し，returnするメソッド
     * □□□
     * □■□
     * □□□
     * このように座標を拡張し，真ん中からの距離を計算，その最短距離をとることで
     * トーラス構造の距離関係を割り出す
     * @param from
     * @param to
     * @return from - to 間の通信時間
     */
    private static int iCalcDelay(Agent from, Agent to) {
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


    /**
     * TODO: 役割の初期化
     * _leader_numだけリーダーを設定する
     * @param agents
     * @param _leader_num
     */
    private static void vSetUpRoles(List<Agent> agents, int _leader_num){
        List<Agent> leaders = new ArrayList<>();
        Agent newLeader;
        int  restAgents;


        for( int i = 0; i < _leader_num; i++ ){
        }
    }

    /**
     * Agent型のリストを引数とし，そのディープコピーを返す関数
     * @param agents
     * @return
     */
    private static List<Agent> lCloneAgents(List<Agent> agents){
        List clones = new ArrayList();

        for( Agent original: agents ){
            clones.add(original.clone());
        }
        return clones;
    }


    /**
     * TODO: 誰を信頼するかは戦略によって違うのでStrategyの方で判断基準というか中身は実装するのが良いか
     */
    private static void vSetUpReliableAgents(List<Agent> agents){

    }

    public static void vReset(){
        int[][] _delays_ = null;
    }


}
