package task;

import others.MyRandom;

import java.util.Arrays;

public class Task {
	private int taskID;
	private int subtaskID = 0;
	// consider: 配列にすべきかListにすべきか
	private Subtask[] components;

	Task( int id, int min_subtasks_num, int max_subtasks_num, int resource_types_num, int min_required_value, int max_required_value ) {
		taskID = id;
		int subtasksNum = MyRandom.getRandomInt( min_subtasks_num, max_subtasks_num );

		components = new Subtask[subtasksNum];
		for( int i = 0; i < subtasksNum; i++ ) {
			int requiredResourceType = MyRandom.getRandomInt( 0, resource_types_num - 1);
			int requiredResourceSize = MyRandom.getRandomInt( min_required_value, max_required_value );
			components[i] = new Subtask( subtaskID++, requiredResourceType, requiredResourceSize );
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(  );
		sb.append( "ID: " + taskID + " consists of " + Arrays.toString(components) + "\n");
		return sb.toString();
	}
}
