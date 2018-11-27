package root;

public interface EnvironmentalConstants {
    int EXPERIMENTAL_TIMES = 10;
    int MAX_TURN = 50000;

    int AGENT_NUM = 500;
    int MAX_X = 51;
    int MAX_Y = 51;
    int MAX_DELAY = 5;

    int RESOURCE_TYPES = 3;
    int MAX_TASKQUEUE_SIZE = 500;


    // 各リソースの最大値と最小値
    int AGENT_MAX_RESOURCE = 5;
    int AGENT_MIN_RESOURCE = 0;

    int TASK_MAX_RESOURCE = 10;
    int TASK_MIN_RESOURCE = 5;


    // タスクに含まれるサブタスク数の最大値と最小値
    int MAX_SUBTASKS = 6;
    int MIN_SUBTASKS = 3;

    // 各初期値
    double INITIAL_E_LEADER = 0.5;
    double INITIAL_E_MEMBER = 0.5;

    double INITIAL_DE_LEADER = 0.5;
    double INITIAL_DE_MEMBER = 0.5;

    int INITIAL_TASK_NUM = 0;
    int INITIAL_LEADER_NUM = 100;

    boolean DO_ROLE_CHANGES = true;

    enum PhasesLeader {
        act1,
        act2,
    }

    enum RoleName {
        leader,
        member,
    }

    enum PhasesMember {
        act1,
        act2,
        act3,
    }
}
