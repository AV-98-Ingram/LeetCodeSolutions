import java.util.PriorityQueue;
import java.util.TreeSet;

class Solution {

    public int maxEvents(int[][] events) {
	TreeSet<int[]> eventTrees = new TreeSet<>((a,b) ->
	    (Integer.compare(a[0], b[0]) == 0 ? Integer.compare(a[2], b[2]) : Integer.compare(a[0], b[0])));
	PriorityQueue<int[]> avails = new PriorityQueue<>((a, b) -> (Integer.compare(a[1], b[1])));

	final int len = events.length;

	for (int i = 0; i < len; i++) {
	    eventTrees.add(new int[] { events[i][0], events[i][1], i });
	}

	int attend = 0;
	int day = eventTrees.first()[0];

	while (!eventTrees.isEmpty()) {
	    for (int[] e : eventTrees.headSet(new int[] { day + 1, day, len })) {
		if (e[0] <= day && e[1] >= day)
		    avails.add(e);
	    }

	    int[] next = eventTrees.ceiling(new int[] { day + 1, day, -1 });
	    int nextDay = -1;

	    if (next != null)
		nextDay = next[0];

	    while (!avails.isEmpty() && day != nextDay) {
		int[] e = avails.poll();

		if (e[1] >= day) {
		    attend++;
		    day++;
		}
		eventTrees.remove(e);
	    }
	    avails.clear();
	    if (nextDay == -1)
		break;
	    day = nextDay;
	}
	return attend;
    }

    public static void main(String[] args) {
	new Solution().maxEvents(new int[][] { { 1, 4 }, { 4, 4 }, { 2, 2 }, { 3, 4 }, { 1, 1 } });
	// [[1,4],[4,4],[2,2],[3,4],[1,1]]
	// [[ 1, 2 ], [ 1, 2 ], [ 3, 3 ], [ 1, 5 ], [ 1, 5 ]]
    }
}
