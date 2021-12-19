class Solution {
	// DP: dp[i][j]: is the max points one can get at row i if
	// selecting j-th column at row i
	public long maxPoints(int[][] points) {
		final int nrows = points.length;
		final int ncols = points[0].length;
		long dp[][] = new long[nrows][ncols];
		// left[j]: the max of (pts[i][k] + k - (pts[i][j] + j)) for (k : 0 .. j):
		long left[] = new long[ncols];
		// right[j]: the max of (pts[i][k] + (ncols-1-k) - (pts[i][j] + (ncols-1-j)))
		// for (k : j .. ncols-1):
		long right[] = new long[ncols];
		long rowMax;

		rowMax = points[0][0];
		for (int i = 0; i < ncols; i++) {
			dp[0][i] = points[0][i];
			rowMax = Math.max(rowMax, points[0][i] + i);
			left[i] = rowMax - (points[0][i] + i);
		}
		rowMax = points[0][ncols - 1];
		for (int i = ncols - 1; i >= 0; i--) {
			rowMax = Math.max(rowMax, points[0][i] + ncols - 1 - i);
			right[i] = rowMax - (points[0][i] + ncols - 1 - i);
		}
		for (int i = 1; i < nrows; i++) {
			for (int j = 0; j < ncols; j++) {
				dp[i][j] = Math.max(left[j], right[j]) + points[i][j] + dp[i - 1][j];
			}
			rowMax = dp[i][0];
			for (int k = 0; k < ncols; k++) {
				rowMax = Math.max(rowMax, dp[i][k] + k);
				left[k] = rowMax - (dp[i][k] + k);
			}
			rowMax = dp[i][ncols - 1];
			for (int k = ncols - 1; k >= 0; k--) {
				rowMax = Math.max(rowMax, dp[i][k] + ncols - 1 - k);
				right[k] = rowMax - (dp[i][k] + ncols - 1 - k);
			}
		}

		long max = dp[nrows - 1][0];

		for (int k = 1; k < ncols; k++)
			max = Math.max(max, dp[nrows - 1][k]);
		return max;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().maxPoints(new int[][] { { 1, 2, 3 }, { 1, 5, 1 }, { 3, 1, 1 } }));
	}
}
