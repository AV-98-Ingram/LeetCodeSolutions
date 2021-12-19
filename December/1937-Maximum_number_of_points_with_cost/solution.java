class Solution {
    // DP: dp[i][j]: is the max points one can get at row i if
    // selecting j-th column at row i
    public long maxPoints(int[][] points) {
	final int nrows = points.length;
	final int ncols = points[0].length;
	long dp[][] = new long[nrows][ncols];

	for (int i = 0; i < ncols; i++)
	    dp[0][i] = points[0][i];

	for (int i = 1; i < nrows; i++)
	    for (int j = 0; j < ncols; j++) {
		long max = dp[i - 1][0] - Math.abs(j - 0) + points[i][j];

		for (int k = 1; k < ncols; k++)
		    max = Math.max(max, dp[i - 1][k] - Math.abs(j - k) + points[i][j]);
		dp[i][j] = max;
	    }

	long max = dp[nrows - 1][0];

	for (int k = 1; k < ncols; k++)
	    max = Math.max(max, dp[nrows - 1][k]);
	return max;
    }
}
