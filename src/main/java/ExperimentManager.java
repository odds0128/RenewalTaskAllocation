import agent.AgentManager;
import com.fasterxml.jackson.databind.JsonNode;
import communication.CommunicationManager;
import task.TaskManager;

import static others.MyRandom.setNewRnd;
import static others.MyRandom.setNewSfmt;

public class ExperimentManager {
	int max_turn_num_;
	AgentManager am;
	TaskManager  tm;
	CommunicationManager cm;

	ExperimentManager( JsonNode jsonNode, String strategyName ) {
		max_turn_num_ = jsonNode.get("environment").get("max_turn_num").asInt();
		am = new AgentManager( jsonNode.get( "agents" ), strategyName );
		tm = new TaskManager( jsonNode.get( "tasks" ) );
		cm = new CommunicationManager( jsonNode.get( "communication" ), am.getAgentList() );
	}

	void doAnExperiment( int times ) {
		setNewSfmt( times );
		setNewRnd( times );

		am.initiateAgents();
		tm.initiateTaskQueue( );

		for( int i = 0; i < max_turn_num_; i++ ) {
			tm.addTasksToTaskQueue();

			am.takeActions();
			// ( role selection )
			// agents action ( leader -> member )
		}
	}
}
