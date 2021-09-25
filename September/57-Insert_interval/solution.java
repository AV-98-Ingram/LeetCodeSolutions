class Solution {
	public int[][] insert(int[][] intervals, int[] newInterval) {
		int i = 0;
		for (; i < intervals.length; i++) {
			int[] itv = intervals[i];

			if (itv[0] > newInterval[0])
				break;
		}

		int extraCell = 1;

		// Attempt to merge with intervals[i-1]. Note that it
		// is impossible for intervals[i-2] being mergable with the
		// newInterval.
		if (i > 0)
			if (intervals[i - 1][1] >= newInterval[0]) {
				// merge:
				newInterval[0] = intervals[i - 1][0];
				if (intervals[i - 1][1] > newInterval[1])
					newInterval[1] = intervals[i - 1][1];
				extraCell--;
			}

		// then merge with intervals at the right-hand side of the newInterval:
		int numMerged = mergeRight(intervals, i, newInterval);
		int[][] ret = new int[intervals.length + extraCell - numMerged][2];

		System.arraycopy(intervals, 0, ret, 0, i - 1 + extraCell);
		ret[i - 1 + extraCell] = newInterval;
		System.arraycopy(intervals, i + numMerged, ret, i + extraCell,
				intervals.length - i - numMerged);
		return ret;
	}

	private int mergeRight(int[][] intervals, int start, int[] newInterval) {
		if (start == intervals.length)
			return 0;

		int[] itv = intervals[start];

		// we know that itv[0] > newInterval[0]
		if (itv[0] <= newInterval[1]) {
			if (itv[1] > newInterval[1])
				newInterval[1] = itv[1];
			// merged:
			return 1 + mergeRight(intervals, start + 1, newInterval);
		}
		return 0;
	}

	public static void main(String args[]) {
		int[][] intervals = new int[][] { { 1, 2 }, { 3, 5 }, { 6, 7 },
				{ 8, 10 }, { 12, 16 } };

		intervals = new Solution().insert(intervals, new int[] { 4, 8 });
		for (int[] itv : intervals)
			System.out.println(itv[0] + ", " + itv[1]);
	}
}
