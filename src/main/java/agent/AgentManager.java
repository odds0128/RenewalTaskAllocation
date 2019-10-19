package agent;

public class AgentManager {
	public AgentManager( String strategyName ) {

	}

	private Agent generateAgent( String strategyName ) {
		return new Agent(strategyName);
	}
}
