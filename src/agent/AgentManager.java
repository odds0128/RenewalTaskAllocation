package agent;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static root.EnvironmentalConstants.*;

public class AgentManager {
    private static AgentManager _singleton_ = new AgentManager();
    public static AgentManager getInstance(){
        return _singleton_;
    }


    private List<Agent>  agents_;    // リーダー→メンバー→役割なしの順番に入れる．
    private int leader_num_;
    private int member_num_;
    private HashMap<Agent, Coordinate> coordinates_;
    private int[][] delays_;         // 添字はエージェントのID．入る値は添字のエージェント間の通信所要時間．

    private AgentManager(){
        coordinates_ = new HashMap<>();
        delays_      = new int[AGENT_NUM][AGENT_NUM];
        System.out.println("AgentManager generated.");
    }

    public void vInitiateAgents(int agent_num, int leader_num){
        agents_      = AgentInitiator.getInstance().lGenerateAgents(agent_num, leader_num);
        coordinates_ = AgentInitiator.getInstance().hSetPositions(agents_);
        delays_      = AgentInitiator.getInstance().aSetDelays(coordinates_, agents_);
    }



    /**
     * Agent型のリストを引数とし，そのディープコピーを返す関数
     */
    private List<Agent> lCloneAgents(List<Agent> agents){
        List<Agent> clones = new ArrayList<>();

        for( Agent original: agents ){
            clones.add(original.clone());
        }
        return clones;
    }

    public void vReset(){

    }

//  TODO: テスト用にリストを返すメソッドを作るけどこれは後で消したい
    public List<Agent> lGetAgents(){
        return agents_;
    }

}
