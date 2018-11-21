package agent;

import Strategies.StrategyBase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static root.EnvironmentalConstants.*;

public class AgentManager {
    private static AgentManager _singleton_ = new AgentManager();
    public static AgentManager getInstance(){
        return _singleton_;
    }


    private StrategyBase strategyLeader;
    private StrategyBase strategyMember;
    private List<Agent>  agents_;    // リーダー→メンバー→役割なしの順番に入れる．
    private int leader_num_ = 0;
    private int member_num_ = 0;
    private HashMap<Agent, Coordinate> coordinates_;
    private int[][] delays_;         // 添字はエージェントのID．入る値は添字のエージェント間の通信所要時間．

    private AgentManager(){
        coordinates_ = new HashMap<>();
        delays_      = new int[AGENT_NUM][AGENT_NUM];
    }

    public void vInitiateAgents(int agent_num, int leader_num){
        this.leader_num_ = leader_num;
        this.member_num_ = agent_num - leader_num;
        agents_      = AgentInitiator.getInstance().lGenerateAgents(agent_num, leader_num);
        coordinates_ = AgentInitiator.getInstance().hSetPositions(agents_);
        delays_      = AgentInitiator.getInstance().aSetDelays(coordinates_, agents_);
    }

    // FIXME: ROLEが固定かどうかは途中で変わることはないので毎ターン判断するようになっているのを直したい
    public void act(){
        if( DO_ROLE_CHANGES ) {
            agents_ = lRearrangeList(agents_);
        }
        for( Agent ag: agents_ ){

        }
    }

    // 役割を選択し，役割によってListを並び替える
    // FIXME:二つのこと一気にやってるの良くないとは思うけど効率考えるとなあ
    private List<Agent> lRearrangeList(List<Agent> previousList){
        List<Agent> newList = new ArrayList<>();
        Agent ag;
        RoleName formerRole, currentRole;

        for( int i = 0; i < AGENT_NUM; i++ ){
            ag = previousList.remove(0);
            formerRole = ag.getRoleName();
            ag.vSelectRole();                            // エージェントの役割選択
            currentRole = ag.getRoleName();

            // leaderエージェントはリストの前半に持ってくる
            if( ag.getRoleName() == RoleName.leader ){
                vCountEachRole(formerRole, currentRole);
                newList.add(0, ag);
            }else{
                vCountEachRole(formerRole, currentRole);
                newList.add(ag);
            }
        }
        assert agents_.size() == 0: "Strange agents size";
        assert newList.size() == AGENT_NUM: "Strange agents size";
        return newList;
    }

    private void vCountEachRole(RoleName former, RoleName current){
        if( former == current ){
            return;
        }else if( current == RoleName.leader ){
            leader_num_++;
            member_num_--;
        }else{
            member_num_++;
            leader_num_--;
        }
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
        agents_      = null;
        leader_num_  = 0;
        member_num_  = 0;
        coordinates_ = null;
    }

    int getLeader_num_() {
        return leader_num_;
    }

    public void vSetStrategy(String strategy)  {
        Class  cll, clm;
        Method ml, mm;

        String packageName = "Strategies.StrategyX.";
        String methodName  = "getInstance";

        try {
            cll = Class.forName(packageName + strategy + "_Leader");
            ml  = cll.getMethod(methodName);
            this.strategyLeader = (StrategyBase) ml.invoke(cll.newInstance());

            clm = Class.forName(packageName + strategy + "_Member");
            mm  = clm.getMethod(methodName);
            this.strategyMember = (StrategyBase) mm.invoke(clm.newInstance());
        } catch (ClassNotFoundException | NoSuchMethodException
                | InstantiationException | IllegalAccessException | InvocationTargetException e) {

            e.printStackTrace();
            System.exit(1);
        }
    }
}
