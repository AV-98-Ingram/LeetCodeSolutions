class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
	final int len = points.length;
	int[] from = points[0];
	int time = 0;
	
	for (int i = 1; i < len; i++) {
	    time += go(from, points[i]);
	    from = points[i];
	}
	return time;
    }

    private int go(int[] p, int[] q) {
	int time = 0;
	int dist0 = Math.abs(p[0] - q[0]);
	int dist1 = Math.abs(p[1] - q[1]);
	
	if (dist0 >= 1 && dist1 >= 1) {
	    // diagnoal:
	    int dist = Math.min(dist0, dist1);

	    time += dist;
	    p[0] = p[0] > q[0] ? p[0] - dist : p[0] + dist;
	    p[1] = p[1] > q[1] ? p[1] - dist : p[1] + dist;
	}

	if (p[0] != q[0])
	    time += Math.abs(p[0] - q[0]);
	if (p[1] != q[1])
	    time += Math.abs(p[1] - q[1]);	
	return time;
    }
}
