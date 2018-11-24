package Strategies.StrategyX;

import Strategies.StrategyBase;
import agent.Agent;
import root.EnvironmentalConstants;

public class StrategyX_Leader extends StrategyBase{
    private static StrategyBase _singleton_ = new StrategyX_Leader();
    private StrategyX_Leader(){
        System.out.println("StrategyX_Leader created");
    }
    public static StrategyBase getInstance(){
        System.out.println("Set StrategyX_Leader");
        return _singleton_;
    }

    public void act(Agent leader){
        assert leader.getRoleName().equals(EnvironmentalConstants.RoleName.leader) : "Not Leader Agent";

        if(leader.getLeaderElements().getCandidates().size() == 0){
            // TODO: タスクをもらってくる
            vSolicit(leader);
        }else{
            vFormTeam(leader);
        }
    }

    private void vSolicit(Agent l){
        //TODO: チーム候補の選定
        //TODO: チーム参加要請の送信
    }

    private void vFormTeam(Agent l){
        // TODO: 返信を確認
        // TODO: DEの更新
        // TODO: チームの編成
        // TODO: タスクを破棄したらbeforeNextTaskを呼んで次に
        // TODO: チーム編成に成功したらbeforeNextTaskを呼んで次に
    }

    // TODO: タスクについてチームが編成できたり，破棄した後で次のタスクに取り掛かる前に呼ばれるメソッドを実装する．
    private void beforeNextTask(){

    }

}
