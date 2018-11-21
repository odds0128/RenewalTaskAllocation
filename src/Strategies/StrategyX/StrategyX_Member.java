package Strategies.StrategyX;

import Strategies.StrategyBase;

public class StrategyX_Member extends StrategyBase{
    private static StrategyX_Member _singleton_ = new StrategyX_Member();
    public static StrategyX_Member getInstance(){
        return _singleton_;
    }
}
