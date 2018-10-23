package agent;


import java.util.ArrayList;
import java.util.List;

import static myUtil.myRandom.*;
import static constants.EnvironmentalConstants.*;

public class AgentManager {
    private static AgentManager _singleton_ = new AgentManager();
    private List<Agent>  agents_;    // リーダー→メンバー→役割なしの順番に入れる．
    private int leader_num_;
    private int member_num_;
    private int[][] coordinates_;    // 入る値はエージェントのID．MAX_X × MAX_Y 座標の該当するいちに誰がいるか．
    private int[][] delays_;         // 添字はエージェントのID．入る値は添字のエージェント間の通信所要時間．

    private AgentManager(){
        System.out.println("AgentManager generated.");
    }




    /**
     * 引数の数だけエージェントを生成し，
     * 座標・遅延時間を設定し，
     * 信頼エージェント・役割の初期値を入れる．
     */
    public void vInitiateAgents(int agent_num, int leader_num) {
        agents_ = lGenerateAgents(agent_num);
        vSetUpPositions(agents_);
        vSetUpDelays(agents_);

    }

    /**
     * 引数の数だけエージェントを生成する
     */
    private static List<Agent> lGenerateAgents(int _agent_num) {
        List<Agent> agentList = new ArrayList<>();
        for (int i = 0; i < _agent_num; i++) {
            agentList.add(new Agent());
        }
        return agentList;
    }


    /**
     * Agentのインスタンス生成時に呼び出される．
     * 全リソースが0にならないようにリソースを設定する
     */
    private int[] setResources(){
        int[] tempResources = new int[RESOURCE_TYPES];
        int resSize = 0;

        int temp ;
        while( true ){
            for( int i = 0; i < RESOURCE_TYPES; i++ ){
                temp = getRandomInt(AGENT_MIN_RESOURCE, AGENT_MAX_RESOURCE);
                resSize          += temp;
                tempResources[i] =  temp;
            }
            if( resSize != 0 ) return tempResources;
            resSize = 0;
        }
    }


    /**
     * 場所が被らないように引数のエージェントに座標を設定する．
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
        }
    }


    /**
     * エージェント間の遅延時間を設定するメソッド．
     * iからjへの通信とjからiへの通信は同じ時間がかかるという前提．
     * なお，i == jの時は0が入っている．
     */
    private void vSetUpDelays(List<Agent> agents) {
        int _size = agents.size();
        Agent from, to;

        for (int iFrom = 0; iFrom < _size; iFrom++) {
            from = agents.get(iFrom);
            for (int iTo = iFrom + 1; iTo < _size; iTo++) {
                to = agents.get(iTo);
                // 自分から相手への通信と相手から自分への通信は同じ時間
                 delays_[iFrom][iTo] = iCalcDelay(from, to);
                 delays_[iTo][iFrom] = iCalcDelay(from, to);
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
     * @return from - to 間の通信時間
     */
    private static int iCalcDelay(Agent from, Agent to) {
        int _tillEnd = MAX_X / 2 + MAX_Y / 2;
        int _minDistance = Integer.MAX_VALUE;
        int _tilesX = 3;
        int _tilesY = 3;

        int fromX = coodinates;
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
     */
    private static List<Agent> lCloneAgents(List<Agent> agents){
        List<Agent> clones = new ArrayList<Agent>();

        for( Agent original: agents ){
            clones.add(original.clone());
        }
        return clones;
    }


    public static AgentManager getInstance(){
        return _singleton_;
    }

    public static void vReset(){

    }

}
