package agent;

import static others.Constants.Role.*;
import agent.strategy.LeaderStrategy;
import agent.strategy.MemberStrategy;
import others.Constants;
import others.Constants.Role;
import others.MyRandom;

public class Agent {
	private static double initial_leader_ratio_ = 0.5;

	int id;
	Role currentRole;
	LeaderStrategy ls;
	MemberStrategy ms;

	Agent( int id, String strategyName ) {
		this.id = id;
		this.ls = LeaderStrategy.parseLeaderStrategy( strategyName );
		this.ms = MemberStrategy.parseMemberStrategy( strategyName );
		currentRole = getRandomRole();
	}

	private Role getRandomRole() {
		return MyRandom.getRandomDouble() < initial_leader_ratio_ ? LEADER : MEMBER;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(  );
		sb.append( String.format( "No.%3d", id ) );
		return sb.toString();
	}

	public void actAsLeader() {
		ls.actAsLeader();
	}

	public void actAsMember() {
		ms.actAsMember();
	}

	public int getId() {
		return id;
	}
}
