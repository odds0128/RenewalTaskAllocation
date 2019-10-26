package communication.message;

import agent.Agent;
import others.Constants.ResultType;
import task.Subtask;

public class ResultOfTeamFormation extends Message {

	static private int results = 0;

	private ResultType result;
	private Subtask allocatedSubtask;

	public ResultOfTeamFormation( Agent from, Agent to, ResultType result, Subtask allocatedSubtask ) {
		super( from, to );
		results++;
		this.result = result;
		this.allocatedSubtask = allocatedSubtask;
	}

	public ResultType getResult() {
		return result;
	}

	public Subtask getAllocatedSubtask() {
		return allocatedSubtask;
	}

	public static int getResults() {
		return results;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder( );
		sb.append( "Result       - " );
		sb.append( "From: " + from );
		sb.append( "To: " + to );
		sb.append( "Result: " + result + ", " );
		sb.append( "AllocatedSubtask: " + allocatedSubtask );
		return sb.toString();
	}

	@Override
	public void clear() {
		results = 0;
	}
}
