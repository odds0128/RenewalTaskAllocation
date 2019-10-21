package task;

public class Subtask {
	private int id;
	private int resourceType;
	private int resourceSize;

	Subtask( int id, int resourceType, int resourceSize ){
		this.id = id;
		this.resourceType = resourceType;
		this.resourceSize = resourceSize;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(  );
		sb.append( "ID: " + id + " requires " + resourceType + " by " + resourceSize );
		return sb.toString();
	}
}
