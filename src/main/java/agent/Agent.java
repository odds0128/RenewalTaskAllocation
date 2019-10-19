package agent;

import agent.strategy.LeaderStrategy;
import agent.strategy.MemberStrategy;

public class Agent {
	int id;
	LeaderStrategy ls;
	MemberStrategy ms;

	Agent( int id, String strategyName ) {
		this.id = id;
		this.ls = LeaderStrategy.parseLeaderStrategy( strategyName );
		this.ms = MemberStrategy.parseMemberStrategy( strategyName );
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(  );
		sb.append( String.format( "No.%3d", id ) );
		return sb.toString();
	}
}
