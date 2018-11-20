package Strategies.strategyX;

import Strategies.Strategy;

public class StrategyX_Member extends Strategy {
    private static Strategy _singleton_ = new StrategyX_Member();
    public static Strategy getInstance(){
        return _singleton_;
    }
}
