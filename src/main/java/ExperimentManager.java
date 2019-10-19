import agent.AgentManager;
import task.TaskManager;

public class ExperimentManager {
	AgentManager am;
	TaskManager  tm;

	ExperimentManager( String strategyName ) {
		am = new AgentManager(strategyName);
		tm = new TaskManager();
	}

	void doExperiment(  ) {

	}
}
