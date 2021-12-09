
import java.util.Arrays;

/**
 * Array-based on-demand computation.  No significant performance improvement.
 *
 */
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
	if (stations.length == 0)
	    return startFuel >= target ? 0 : -1;
	if (startFuel < stations[0][0])
	    return -1;

	long[][] dp = new long[stations.length][stations.length + 1];
	boolean[][] ready = new boolean[stations.length][stations.length + 1];

	for (int i = 0; i < stations.length; i++)
	    Arrays.fill(ready[i], false);

	dp[0][0] = Long.valueOf(startFuel - stations[0][0]);
	dp[0][1] = dp[0][0] + stations[0][1];
	ready[0][0] = true;
	ready[0][1] = true;
	int i = 1;
	int minStops = 0;

	for (; i < stations.length && stations[i][0] < target; i++) {
	    for (int j = minStops; j <= i + 1; j++) {
		long maxFuel = getMaxFuel(dp, ready, stations, i, j);

		if (maxFuel < 0) {
		    minStops = j + 1;
		    if (minStops > i + 1)
			return -1;
		} else
		    break;
	    }
	}
	for (int j = minStops; j <= i; j++) {
	    if (getMaxFuel(dp, ready, stations, i - 1, j) >= target - stations[i - 1][0])
		return j;
	}
	return -1;
    }

    // compute on-demand dp:
    private long getMaxFuel(long[][] dp, boolean ready[][], int[][] stations, int station, int stops) {
	if (stops < 0 || stops > station + 1)
	    return -1;
	if (station == 0)
	    return dp[station][stops];

	if (ready[station][stops])
	    return dp[station][stops];

	long noStopHere = getMaxFuel(dp, ready, stations, station - 1, stops);
	long stopHere = getMaxFuel(dp, ready, stations, station - 1, stops - 1);
	long dist = stations[station][0] - stations[station - 1][0];
	long gas = stations[station][1];
	long maxFuel;
	// we have to filter out negative 'stopHere - dist' because it may become
	// positive
	// after adding 'gas' but in fact it means one cannot reach this gas station:
	if (stopHere - dist < 0)
	    maxFuel = noStopHere - dist;
	else
	    maxFuel = Math.max(noStopHere - dist, stopHere - dist + gas);
	dp[station][stops] = maxFuel;
	ready[station][stops] = true;
	return maxFuel;
    }

    public static void main(String[] args) {
	System.out.println(new Solution().minRefuelStops(200, 50,
							 new int[][] { { 25, 25 }, { 50, 100 }, { 100, 100 }, { 150, 40 } }));
    }
}
