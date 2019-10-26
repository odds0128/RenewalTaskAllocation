package communication.message;

import agent.Agent;

public abstract class Message {
	Agent from;
	Agent to;

	Message( Agent from, Agent to ) {
		this.from = from;
		this.to = to;
	}

	public Agent getFrom() {
		return from;
	}

	public Agent getTo() {
		return to;
	}


	abstract public void clear();
}
