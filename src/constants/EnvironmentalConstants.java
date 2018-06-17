package constants;

public class EnvironmentalConstants {
    private EnvironmentalConstants(){}

    public static final int AGENT_NUM = 500;
    public static final int MAX_X = 51;
    public static final int MAX_Y = 51;
    public static final int MAX_DELAY = 5;

    public static final int AGENT_MAX_RESOURCE = 5;
    public static final int AGENT_MIN_RESOURCE = 0;

    public static final int TASK_MAX_RESOURCE = 10;
    public static final int TASK_MIN_RESOURCE = 5;


    public enum Roles{
        LEADER,
        MEMBER,
    }

    public enum Phases{
    }

}
