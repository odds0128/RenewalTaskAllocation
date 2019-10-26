package communication.message;

import agent.Agent;
import task.Subtask;

public class Solicitation extends Message {
	static private int solicitations = 0;
	private Subtask expectedSubtask;

	public Solicitation( Agent from, Agent to, Subtask subtask ) {
		super( from, to );
		solicitations++;
		this.expectedSubtask = subtask;
	}

	public Subtask getExpectedSubtask() {
		return expectedSubtask;
	}

	public static int getSolicitations() {
		return solicitations;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "Solicitation - " );
		sb.append( "From: " + from );
		sb.append( "To: " + to );
		sb.append( "Subtask: " + expectedSubtask );
		return sb.toString();
	}

	@Override
	public void clear(){
		solicitations = 0;
	}
}
