package agent;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class AgentManager {
	private int agentId = 0;
	private int agents_num_;

	public List< Agent > getAgentList() {
		return agentList;
	}

	private List<Agent> agentList ;
	private String strategyName;

	public AgentManager( JsonNode jsonNode, String strategyName ) {
		this.agents_num_  = jsonNode.get( "agents_num" ).asInt();
		this.strategyName = strategyName;
		agentList = generateAgentList();
		// todo: agentsが揃ってからではないとできない処理を入れる
		setReliabilities();
	}

	private List<Agent> generateAgentList() {
		List<Agent> tempList = new ArrayList<>(  );
		for( int i = 0; i <agents_num_; i++ ) {
			tempList.add( generateAnAgent() );
		}
		return tempList;
	}

	private Agent generateAnAgent() {
		return new Agent( agentId++, strategyName );
	}

	private void setReliabilities() {

	}
}
