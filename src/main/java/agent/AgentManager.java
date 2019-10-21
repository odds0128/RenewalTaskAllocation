package agent;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

import static others.Constants.Role.*;

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
	}

	public void initiateAgents(  ) {
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

	public void takeActions() {
		agentList.stream()
			.filter( ag -> ag.currentRole == LEADER )
			// todo: 行動
			.forEach( ag -> ag.actAsLeader() );
		agentList.stream()
			.filter( ag -> ag.currentRole == MEMBER )
			.forEach( ag -> ag.actAsMember() );
	}
}
