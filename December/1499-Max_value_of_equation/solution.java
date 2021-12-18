class Solution {

    // brutal force: 
    public int findMaxValueOfEquation(int[][] points, int k) {
	int max = Integer.MIN_VALUE;
	final int len = points.length;

	for (int i = 0; i < len; i++)
	    for (int j = i + 1; j < len; j++) {
		if (points[j][0] - points[i][0] <= k)
		    max = Math.max(max, points[j][1] + points[i][1] + points[j][0] - points[i][0]);
		else
		    break;
	    }
	return max;
    }   
}
