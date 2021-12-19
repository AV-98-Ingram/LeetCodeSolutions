import java.util.PriorityQueue;

class Solution {

    static class Task {
	int start, time, id;

	Task(int start, int time, int id) {
	    this.start = start;
	    this.time = time;
	    this.id = id;
	}

	static int compare(Task t1, Task t2) {
	    int comp = Integer.compare(t1.time, t2.time);

	    if (comp == 0)
		return Integer.compare(t1.id, t2.id);
	    return comp;
	}
    }

    public int[] getOrder(int[][] tasks) {
	PriorityQueue<Task> startQue = new PriorityQueue<>((a, b) -> (Integer.compare(a.start, b.start)));
	PriorityQueue<Task> timeQue = new PriorityQueue<>((a, b) -> (Task.compare(a, b)));
	int[] result = new int[tasks.length];
	int time = 0;
	int i = 0;
	final int len = tasks.length;

	// initialize startQue
	for (int j = 0; j < len; j++)
	    startQue.add(new Task(tasks[j][0], tasks[j][1], j));
	// an invariant of timeQue: all tasks in queue can be started at current time
	while (i < len) {
	    while (!startQue.isEmpty() && startQue.peek().start <= time)
		timeQue.add(startQue.poll());
	    if (!timeQue.isEmpty()) {
		Task task = timeQue.poll();

		time += task.time;
		result[i++] = task.id;
	    } else {
		Task task = startQue.poll();

		time = task.start;
		timeQue.add(task);
	    }
	}
	return result;
    }
}
