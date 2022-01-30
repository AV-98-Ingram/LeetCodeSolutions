import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

class Solution {
    public int minTaps(int n, int[] ranges) {
	TreeSet<int[]> taps = new TreeSet<>((a, b) -> Integer.compare(a[0] + a[1], b[0] + b[1]));
	Map<Integer, Integer> maxRangeAtEnding = new HashMap<>();
	int i = 0;

	for (int range : ranges) {
	    int max = maxRangeAtEnding.getOrDefault(range + i, 0);

	    // drop 1) tap of range 0 is useless, and
	    // 2) tap reaches same end as another one but has smaller range.
	    if (range > max) {
		taps.add(new int[] { range, i });
		maxRangeAtEnding.put(range + i, range);
	    }
	    i++;
	}
	if (taps.isEmpty() || taps.last()[0] + taps.last()[1] < n)
	    return -1;

	// DP[i]: the min number of taps at the position reached by tap[i]
	int dp[] = new int[n + 1];
	Iterator<int[]> iter = taps.iterator();
	int[] firstTap = iter.next();
	int minResult = -1;

	dp[firstTap[1]] = firstTap[1] - firstTap[0] <= 0 ? 1 : 0;
	while (iter.hasNext()) {
	    int[] tap = iter.next();

	    i = tap[1];
	    if (tap[1] - tap[0] <= 0) {
		dp[i] = 1;
	    } else {
		int[] minPrev = null;

		// select the minPrev among the taps that "connect" with the current tap
		for (int[] prevCand : taps.subSet(new int[] { -tap[0], tap[1] }, tap)) {
		    if (minPrev == null || dp[minPrev[1]] == 0)
			minPrev = prevCand;
		    else if (dp[prevCand[1]] > 0)
			if (dp[prevCand[1]] < dp[minPrev[1]])
			    minPrev = prevCand;
		}
		if (minPrev == null) {
		    dp[i] = 0;
		} else {
		    if (dp[minPrev[1]] == 0) // neither cover point 0
			dp[i] = 0;
		    //					else if (prev[0] + prev[1] == tap[0] + tap[1]) // prev covers this
		    //						dp[i] = dp[prev[1]];
		    //					else if (prev[1] - prev[0] >= tap[1] - tap[0]) // this covers prev
		    //						dp[i] = dp[prev[1]];
		    else // previous connects this
			dp[i] = dp[minPrev[1]] + 1;
		}
	    }
	    // update min result:
	    if (tap[0] + tap[1] >= n && dp[i] > 0)
		minResult = minResult > 0 ? Math.min(dp[i], minResult) : dp[i];
	}
	return minResult;
    }
}
