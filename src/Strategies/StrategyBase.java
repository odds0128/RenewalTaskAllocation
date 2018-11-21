package Strategies;

public abstract class StrategyBase {
    private static StrategyBase _singleton_;

    public static StrategyBase getInstance(){
        return _singleton_;
    }
}
