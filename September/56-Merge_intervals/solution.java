import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {

	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, (a1, a2) -> Integer.compare(a1[0], a2[0]));
		List<int[]> result = keepMerge(intervals, 0);
		int[][] ret = new int[result.size()][2];

		result.toArray(ret);
		return ret;
	}

	private List<int[]> keepMerge(int[][] intervals, int start) {
		if (start >= intervals.length - 1) {
			List<int[]> suffix = new LinkedList<>();

			suffix.add(intervals[start]);
			return suffix;
		}

		int[] interval = intervals[start];
		int[] next = intervals[start + 1];

		if (interval[1] >= next[0]) {
			int maxUpperBound = interval[1] > next[1] ? interval[1] : next[1];

			next[0] = interval[0];
			next[1] = maxUpperBound;
			return keepMerge(intervals, start + 1);
		} else {
			// no merge, next
			List<int[]> suffix = keepMerge(intervals, start + 1);

			suffix.add(intervals[start]);
			return suffix;
		}
	}
}
