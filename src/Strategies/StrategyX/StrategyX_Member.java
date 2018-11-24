package Strategies.StrategyX;

import Strategies.StrategyBase;
import agent.Agent;
import root.EnvironmentalConstants.RoleName;

public class StrategyX_Member extends StrategyBase{
    private static StrategyX_Member _singleton_ = new StrategyX_Member();
    private StrategyX_Member(){
        System.out.println("StrategyX_Member created");
    }
    public static StrategyBase getInstance(){
        System.out.println("Set StrategyX_Member");
        return _singleton_;
    }

    public void act(Agent member){
        assert member.getRoleName().equals(RoleName.member) : "Not Member Agent";

        if( member.getMemberElements().getLeader() == null ){  // リーダーがまだ決まっていない
            vSelectLeader(member);
        }else{      // サブタスクの実行
            // TODO: 返信の確認
            // TODO: DEの更新
            // TODO: 何ももらえなかったらbeforeNextSubtask
            vExecuteSubtask(member);
        }
    }


    private void vSelectLeader(Agent m){
        // TODO: mail boxの確認
        // TODO: Leader候補の選定
        // TODO: Leader候補への参加表明
    }

    private void vExecuteSubtask(Agent m){
        // TODO: もらったサブタスクの実行
        // TODO: 終わったらbeforeNextSubtask
    }


    // TODO: チームに参加できなかったり，サブタスクが終わったら呼ばれる
    private void beforeNextSubtask(){

    }
}
