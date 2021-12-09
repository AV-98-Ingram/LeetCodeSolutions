import java.util.PriorityQueue;

class Solution {

	public int minRefuelStops(int target, int startFuel, int[][] stations) {
		if (stations.length == 0)
			return startFuel - target >= 0 ? 0 : -1;

		PriorityQueue<Integer> pastStations = new PriorityQueue<>(
				(a, b) -> Integer.compare(stations[b][1], stations[a][1]));

		int minStops = 0;
		int dist = startFuel; // the distance can go
		int i = 0;

		while (dist < target && i <= stations.length) {
			if (i < stations.length && dist >= stations[i][0]) {
				pastStations.add(i);
				i++;
				continue;
			}

			Integer j = pastStations.poll();

			if (j == null)
				return -1;
			minStops++;
			dist += stations[j][1];
		}
		return minStops;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().minRefuelStops(1000, 299, new int[][] { { 13, 21 }, { 26, 115 }, { 100, 47 },
				{ 225, 99 }, { 299, 141 }, { 444, 198 }, { 608, 190 }, { 636, 157 }, { 647, 255 }, { 841, 123 } }));
	}
}
