package agent.strategy;

public class LeaderStrategy {
	private double e_leader = 0;

	public static LeaderStrategy parseLeaderStrategy( String strategyName ) {
		return null;
	}

	private void updateELeader() {
		e_leader = 0;
	}

	public void actAsLeader() {
	}

	public double getE_leader() {
		return e_leader;
	}
}
