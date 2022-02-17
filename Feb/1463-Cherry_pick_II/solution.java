class Solution {

    /*
     * DP[i][j][k]: the max cherries if bot1 at (i, j) and bot2 at (k, j)
     */

    int nrows, ncols, dp[][][];

    public int cherryPickup(int[][] grid) {
	nrows = grid.length;
	ncols = grid[0].length;
	dp = new int[nrows][ncols][ncols];
	dp[0][0][ncols - 1] = grid[0][0] + grid[0][ncols - 1];

	int result = 0;

	for (int i = 1; i < nrows; i++)
	    for (int j = 0; j < ncols - 1; j++)
		for (int k = j + 1; k < ncols; k++) {
		    if (0 <= j && j <= i && k < ncols && k >= ncols - 1 - i) {
			dp[i][j][k] = max(cherries(grid, i - 1, j, k),
					  cherries(grid, i - 1, j - 1, k),
					  cherries(grid, i - 1, j + 1, k),
					  cherries(grid, i - 1, j, k - 1),
					  cherries(grid, i - 1, j - 1, k - 1),
					  cherries(grid, i - 1, j + 1, k - 1),
					  cherries(grid, i - 1, j, k + 1),
					  cherries(grid, i - 1, j - 1, k + 1),
					  cherries(grid, i - 1, j + 1, k + 1)) + grid[i][j] + grid[i][k];
			result = Math.max(dp[i][j][k], result);
		    }
		}
	return result;
    }
    
    private int cherries(int[][] grid, int i, int j, int k) {
	if (0 <= j && k < ncols && j < k)
	    return dp[i][j][k];
	return 0;
    }
    
    private int max(int... nums) {
	int max = 0;
	
	for (int n : nums)
	    max = Math.max(n, max);
	return max;
    }

    public static void main(String[] args) {
	new Solution().cherryPickup(new int[][] { { 0, 8, 7, 10, 9, 10, 0, 9, 6 },
						  { 8, 7, 10, 8, 7, 4, 9, 6, 10 },
						  { 8, 1, 1, 5, 1, 5, 5, 1, 2 },
						  { 9, 4, 10, 8, 8, 1, 9, 5, 0 },
						  { 4, 3, 6, 10, 9, 2, 4, 8, 10 },
						  { 7, 3, 2, 8, 3, 3, 5, 9, 8 },
						  { 1, 2, 6, 5, 6, 2, 0, 10, 0 } });
    }
}
