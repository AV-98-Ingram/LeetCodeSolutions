class Solution {

    static int MAX = 200 * 200 * 100;
    int[][] cache;
    
    public int minPathSum(int[][] grid) {
        int nr = grid.length;
	int nc = grid[0].length;

	cache = new int[nr][nc];
	for (int i = 0; i < nr; i++)
	    Arrays.fill(cache[i], -1);
	return reach(0, 0, grid, nr, nc);
    }

    // returns the shortest path from [r, c] to the right-bottom
    // corner
    private int reach(int r, int c, int[][] grid, int nr, int nc) {
	if (r == nr-1 && c == nc-1)
	    return grid[r][c];
	if (cache[r][c] >= 0)
	    return cache[r][c];

	int min = MAX;
	
	if (r < nr - 1) {
	    int p = reach(r + 1, c, grid, nr, nc) + grid[r][c];

	    min = p;
	}
	if (c < nc - 1) {
	    int p = reach(r, c + 1, grid, nr, nc) + grid[r][c];

	    min = p < min ? p : min;
	}
	cache[r][c] = min;
	return min;
    }
}
