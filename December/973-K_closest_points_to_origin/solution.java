class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> compare(a, b));
	int[][] ret = new int[k][];

	for (int[] pt : points)
	    pq.add(pt);
	k--;
	while (k >= 0) {
	    ret[k--] = pq.poll();
	}
	return ret;
    }

    private int compare(int[] a, int[] b) {
	int a0 = Math.abs(a[0]);
	int a1 = Math.abs(a[1]);
	int b0 = Math.abs(b[0]);
	int b1 = Math.abs(b[1]);

	if (a0 == b0)
	    if (a1 > b1)
		return 1;
	    else if (a1 == b1)
		return 0;
	    else
		return -1;
	if (a1 == b1)
	    if (a0 > b0)
		return 1;
	    else if (a0 == b0)
		return 0;
	    else
		return -1;
	
	return Integer.compare(a0*a0 + a1*a1, b0*b0 + b1*b1);
    }
}
