package communication;

import agent.Agent;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class CommunicationManager {
	private GridAndDelays grid;

	public CommunicationManager( JsonNode jsonNode, List< Agent > agentList ) {
		grid = new GridAndDelays( jsonNode, agentList );
	}
}
