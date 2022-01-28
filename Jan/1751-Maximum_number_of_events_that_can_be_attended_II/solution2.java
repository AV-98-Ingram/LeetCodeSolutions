import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

class Solution {
    static final List<Integer> emptyList = new LinkedList<>();

    // typical DP, memory exceeding limit due to event end time could be huge
    public int maxValue(int[][] events, int k) {
	Map<Integer, List<Integer>> eiEvents = new HashMap<>(); // end day indexed events
	TreeSet<int[]> heap = new TreeSet<>((a, b) ->
					    Integer.compare(a[0], b[0]) == 0 ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

	Arrays.sort(events,
		    (a, b) -> Integer.compare(a[1], b[1]));

	final int len = events.length;

	for (int i = 0; i < len; i++) {
	    eiEvents.computeIfAbsent(events[i][1], key -> new LinkedList<>()).add(i);
	    heap.add(new int[] { events[i][1], i });
	}
	// dp[i][j] max values after event i after taking (at most) j events
	int[][] dp = new int[len][k + 1];
	int totalMax;

	dp[0][0] = 0;
	dp[0][1] = events[0][2];
	totalMax = events[0][2];
	for (int i = 1; i < len; i++) {
	    int ub = Math.min(k, i + 1);

	    for (int j = 1; j <= ub; j++) {
		int max = dp[i - 1][j];
		int x;

		int[] tmp = heap.lower(new int[] { events[i][0], 0 });
		x = tmp == null ? -1 : tmp[1];
		if (x >= 0)
		    max = Math.max(max, dp[x][j - 1] + events[i][2]);
		else
		    max = Math.max(max, events[i][2]);
		dp[i][j] = max;
		totalMax = Math.max(totalMax, max);
	    }
	}
	return totalMax;
    }

    public static void main(String[] args) {
	new Solution().maxValue(new int[][] { { 1, 3, 4 }, { 2, 4, 1 }, { 1, 1, 4 }, { 3, 5, 1 }, { 2, 5, 5 } }, 1);
	// { { 1, 1, 1 }, { 2, 2, 2 }, { 3, 3, 3 }, { 4, 4, 4 } }
	// { { 1, 3, 4 }, { 2, 4, 1 }, { 1, 1, 4 }, { 3, 5, 1 }, { 2, 5, 5 } }
    }
}
