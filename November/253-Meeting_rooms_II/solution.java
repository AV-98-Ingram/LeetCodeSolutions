import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * I was trying to convert this problem to an acyclic graph: <code> 
 * 
 * Suppose all the intervals are sorted by their start time.  We use their sorted positions as their IDs.
 * An interval is a node in a graph. There is a directed edge from node i to node j iff 
 * 
 *  1) i and j do not overlap and 
 *  2) i < j
 * 
 * Note that condition 2 guarantees that there is no cycle.
 * 
 * The most useful property of the graph is that all nodes (intervals) that can share one room must share a common path.
 * (of course we need a proof for it)
 * 
 * So the problem is reduced to remove and count paths from the acyclic graph.  
 * Each path has to be maximal. It represents a room. Note that a single node is also a path.
 * 
 * Example: intervals are { {0, 10}, {1, 4}, {2, 4}, {3, 4}, {4, 6}, {5, 6} }
 *          graph is
 *          0   1 -> 4   1 -> 5
 *              2 -> 4   2 -> 5
 *              3 -> 4   3 -> 5
 *          
 * By removing and counting, we always get 4 paths. One possible result is {0, 1->4, 2->5, 3}  
 * </code>
 * 
 * Unfortunately, this algorithm is not fast enough to pass the last LC test.
 * Complexity is <code>O(|node|^2) + O(|edges|)</code>
 * </p>
 * 
 * @author ziqing
 *
 */
class Solution {

	public int minMeetingRooms(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> (Integer.compare(a[0], b[0])));
		Map<Integer, List<Integer>> edges = new HashMap<>();
		final int ub = intervals.length;

		for (int i = 0; i < ub; i++) {
			for (int j = i + 1; j < ub; j++) {
				if (nonoverlapping(intervals[i], intervals[j]))
					edges.computeIfAbsent(i, k -> new LinkedList<>()).add(j);
			}
			if (!edges.containsKey(i))
				edges.computeIfAbsent(i, k -> new LinkedList<>());
		}

		int counter = 0;

		for (int i = 0; i < ub; i++) {
			// remove a path start from i:
			int pathLen = 0;
			Integer node = i;
			List<Integer> nexts = edges.get(node);

			while (nexts != null) {
				pathLen++;
				edges.remove(node);

				List<Integer> nextNexts = null;

				while (!nexts.isEmpty() && nextNexts == null) {
					node = nexts.remove(0);
					nextNexts = edges.get(node);
				}
				nexts = nextNexts;
			}
			if (pathLen > 0)
				counter++; // if path exists, increases the counter
		}
		return counter;
	}

	private boolean nonoverlapping(int[] earlierStart, int[] laterStart) {
		return earlierStart[1] <= laterStart[0];
	}

	public static void main(String[] args) {
		new Solution().minMeetingRooms(
				new int[][] { { 0, 30 }, { 5, 10 }, { 15, 20 } });
	}
}
