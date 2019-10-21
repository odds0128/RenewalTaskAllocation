package task;

import com.fasterxml.jackson.databind.JsonNode;
import others.MyRandom;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
	private int initial_tasks_num_;
	private int task_queue_size_;
	private double additional_tasks_num_;
	private int min_subtasks_num_;
	private int max_subtasks_num_;
	private int resource_types_num_;
	private int min_request_value_;
	private int max_request_value_;
	private List<Task> taskQueue = new ArrayList<>(  );

	private int overflowTasks = 0;
	private int taskID = 0;

	public TaskManager( JsonNode jsonNode ){
		additional_tasks_num_ = jsonNode.get( "additional_tasks_num" ).asDouble();
		task_queue_size_ =  jsonNode.get( "task_queue_size" ).asInt();
		min_subtasks_num_ = jsonNode.get( "min_subtasks_num" ).asInt();
		max_subtasks_num_ = jsonNode.get( "max_subtasks_num" ).asInt();
		JsonNode subtaskJsonNode = jsonNode.get( "subtasks" );
		resource_types_num_ = subtaskJsonNode.get( "resource_types_num" ).asInt();
		min_request_value_ = subtaskJsonNode.get( "min_request_value" ).asInt();
		max_request_value_ = subtaskJsonNode.get( "max_request_value" ).asInt();
		initial_tasks_num_ = jsonNode.get( "initial_tasks_num" ).asInt();
	};

	public void initiateTaskQueue() {
		addTasksToTaskQueue( initial_tasks_num_ );
		System.out.println( taskQueue );
	}

	public void addTasksToTaskQueue() {
		int room = task_queue_size_ - taskQueue.size();    // タスクキューの空き
		int poisson = poissonDistribution();
		int overflow = poisson > room ? poisson - room : 0;
		int additionalTasksNum = Math.min( poisson, room );

		for ( int i = 0; i < additionalTasksNum; i++ ) {
			addTasksToTaskQueue( additionalTasksNum );
		}
		overflowTasks += overflow;
	}

	private void addTasksToTaskQueue( int additionalTasksNum ) {
		for( int i = 0; i < additionalTasksNum; i++ ) {
			taskQueue.add( new Task( taskID++, min_subtasks_num_, max_subtasks_num_, resource_types_num_, min_request_value_, max_request_value_ ) );
		}
	}

	private int poissonDistribution() {
		double xp;
		int k = 0;
		xp = Math.random();
		while ( xp >= Math.exp( -1 * additional_tasks_num_ ) ){
			xp = xp * MyRandom.getRandomDouble();
			k = k + 1;
		}
		return ( k );
	}
}
