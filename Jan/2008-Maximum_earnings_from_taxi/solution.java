import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    static final List<int[]> emptyList = new LinkedList<>();

    public long maxTaxiEarnings(int n, int[][] rides) {
	long[] dp = new long[n + 1]; // dp[i]: max earn end at location i

	// preprocess rides:
	Map<Integer, List<int[]>> eiRides = new HashMap<>(); // end indexed rides

	for (int[] ride : rides)
	    eiRides.computeIfAbsent(ride[1], k -> new LinkedList<>()).add(ride);
	dp[0] = 0;
	for (int i = 1; i <= n; i++) {
	    long max = dp[i - 1];

	    for (int[] ride : eiRides.getOrDefault(i, emptyList))
		max = Math.max(max, dp[ride[0]] + ride[2] + (ride[1] - ride[0]));
	    dp[i] = max;
	}
	return dp[n];
    }

    public static void main(String[] args) {
	new Solution().maxTaxiEarnings(5, new int[][] { { 2, 5, 4 }, { 1, 5, 1 } });
    }
}
