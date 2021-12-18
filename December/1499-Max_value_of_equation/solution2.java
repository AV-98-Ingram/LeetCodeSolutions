import java.util.PriorityQueue;

class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
	final int len = points.length;
	PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(b[0], a[0])));
	Integer max = null;

	for (int i = 0; i < len; i++) {
	    int part2 = points[i][0] + points[i][1];
	    int[] part1 = null;

	    while (!pq.isEmpty()) {
		part1 = pq.peek();
		if (points[i][0] - part1[1] <= k)
		    break;
		part1 = null;
		pq.poll();
	    }
	    pq.add(new int[] { points[i][1] - points[i][0] , points[i][0]});
	    if (part1 == null)
		continue;

	    int result = part1[0] + part2;

	    if (max != null)
		max = Math.max(max, result);
	    else
		max = result;
	}
	return max;
    }
}
