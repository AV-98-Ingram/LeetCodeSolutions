class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
	long dp[][] = new long[stations.length][stations.length + 1];

	if (stations.length <= 0) {
	    if (target <= startFuel)
		return 0;
	    return -1;
	}
	dp[0][0] = startFuel - stations[0][0];
	if (dp[0][0] < 0)
	    return -1;
	dp[0][1] = dp[0][0] + stations[0][1];

	int i = 1;
	int minStop = 0;
	for (; i < stations.length && stations[i][0] <= target; i++) {
	    for (int j = minStop; j <= i + 1; j++) {
		int dist = stations[i][0] - stations[i - 1][0];

		if (j - 1 < minStop) {
		    dp[i][j] = dp[i - 1][j] - dist;
		} else
		    dp[i][j] = Math.max(dp[i - 1][j] - dist,
					dp[i - 1][j - 1] - dist + stations[i][1]);
		if (dp[i][j] < 0)
		    minStop = j + 1;
	    }
	}
	// find the min j such that dp[i-1][j] >= (target - stations[i-1][0])
	for (int j = minStop; j <= i; j++)
	    if (dp[i - 1][j] >= target - stations[i - 1][0])
		return j;
	return -1;
    }

    public static void main(String[] args) {

	System.out.println(new Solution().minRefuelStops(1000, 83,
							 new int[][] { { 25, 27 }, { 36, 187 }, { 140, 186 }, { 378, 6 },
								       { 492, 202 }, { 517, 89 }, { 579, 234 }, { 673, 86 },
								       { 808, 53 }, { 954, 49 } }));
    }
}
