package Strategies.strategyX;

import Strategies.Strategy;

public class StrategyX_Leader extends Strategy {
    private static Strategy _singleton_ = new StrategyX_Leader();
    public static Strategy getInstance(){
        return _singleton_;
    }

}
