import agent.AgentManager;
import com.fasterxml.jackson.databind.JsonNode;
import task.TaskManager;

public class ExperimentManager {
	AgentManager am;
	TaskManager  tm;

	ExperimentManager( JsonNode jsonNode, String strategyName ) {
		am = new AgentManager( jsonNode.get( "agents" ), strategyName );
		tm = new TaskManager();
		System.out.println( am.getAgentList() );
	}

	void doAnExperiment(  ) {

	}
}
