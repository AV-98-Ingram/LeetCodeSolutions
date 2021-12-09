
import java.util.HashMap;
import java.util.Map;

/**
 * This is the on-demand solution, which is even slower probably because of
 * using of maps.
 *
 */
class Solution {
	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		if (stations.length == 0)
			return startFuel >= target ? 0 : -1;
		if (startFuel < stations[0][0])
			return -1;

		Map<Integer, Map<Integer, Long>> dp = new HashMap<>();
		Map<Integer, Long> first = new HashMap<>();

		dp.put(0, first);
		first.put(0, Long.valueOf(startFuel - stations[0][0]));
		first.put(1, first.get(0) + stations[0][1]);

		int i = 1;
		int minStops = 0;

		for (; i < stations.length && stations[i][0] < target; i++) {
			for (int j = minStops; j <= i + 1; j++) {
				long maxFuel = getMaxFuel(dp, stations, i, j);

				if (maxFuel < 0) {
					minStops = j + 1;
					if (minStops > i + 1)
						return -1;
				} else
					break;
			}
		}
		for (int j = minStops; j <= i; j++) {
			if (getMaxFuel(dp, stations, i - 1, j) >= target - stations[i - 1][0])
				return j;
		}
		return -1;
	}

	// compute on-demand dp:
	private long getMaxFuel(Map<Integer, Map<Integer, Long>> dp, int[][] stations, int station, int stops) {
		if (station == 0)
			return dp.get(station).getOrDefault(stops, Long.valueOf(-1));

		Map<Integer, Long> stationRecord = dp.computeIfAbsent(station, k -> new HashMap<>());
		Long maxFuel = stationRecord.get(stops);

		if (maxFuel != null)
			return maxFuel;

		long noStopHere = getMaxFuel(dp, stations, station - 1, stops);
		long stopHere = getMaxFuel(dp, stations, station - 1, stops - 1);
		long dist = stations[station][0] - stations[station - 1][0];
		long gas = stations[station][1];

		// we have to filter out negative 'stopHere - dist' because it may become
		// positive
		// after adding 'gas' but in fact it means one cannot reach this gas station:
		if (stopHere - dist < 0)
			maxFuel = noStopHere - dist;
		else
			maxFuel = Math.max(noStopHere - dist, stopHere - dist + gas);
		stationRecord.put(stops, maxFuel);
		return maxFuel;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().minRefuelStops(200, 50,
				new int[][] { { 25, 25 }, { 50, 100 }, { 100, 100 }, { 150, 40 } }));
	}
}
