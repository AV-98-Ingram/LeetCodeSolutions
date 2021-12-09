import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* Doing fixed point computation, seems not fast enough.
 */
class Solution {
    public int shortestPath(int[][] grid, int k) {
	final int nrows = grid.length;
	final int ncols = grid[0].length;
	Integer dp[][][] = new Integer[nrows][ncols][k + 1];
	int kk = 0;

	for (kk = 0; kk <= k; kk++)
	    dp[0][0][kk] = 0;
	kk = 0;

	boolean updated = true;
	int ret[];

	while (updated) {
	    updated = false;
	    for (int i = 0; i < nrows; i++)
		for (int j = 0; j < ncols; j++) {
		    ret = compute(grid, dp, i, j, nrows, ncols, Math.min(k, kk), k);
		    kk = ret[0];
		    updated |= ret[1] == 1;
		}
	    for (int i = 0; i < nrows; i++)
		for (int j = ncols - 1; j >= 0; j--) {
		    ret = compute(grid, dp, i, j, nrows, ncols, Math.min(k, kk), k);
		    kk = ret[0];
		    updated |= ret[1] == 1;
		}
	    for (int i = nrows - 1; i >= 0; i--)
		for (int j = 0; j < ncols; j++) {
		    ret = compute(grid, dp, i, j, nrows, ncols, Math.min(k, kk), k);
		    kk = ret[0];
		    updated |= ret[1] == 1;
		}
	    for (int i = nrows - 1; i >= 0; i--)
		for (int j = ncols - 1; j >= 0; j--) {
		    ret = compute(grid, dp, i, j, nrows, ncols, Math.min(k, kk), k);
		    kk = ret[0];
		    updated |= ret[1] == 1;
		}
	}
	int min = -1;

	for (int i = 0; i <= k; i++)
	    if (dp[nrows - 1][ncols - 1][i] != null && (dp[nrows - 1][ncols - 1][i] < min || min < 0))
		min = dp[nrows - 1][ncols - 1][i];
	return min;
    }

    private int[] compute(int grid[][], Integer dp[][][], int i, int j, final int nrows, final int ncols, final int k,
			  final int kb) {
	List<Integer> neibs = new LinkedList<>();
	int updated = 0;

	for (int kk = 0; kk <= k; kk++) {
	    if (dp[i][j][kk] != null)
		neibs.add(dp[i][j][kk] - 1);
	    if (i > 0) {
		// top:
		if (dp[i - 1][j][kk] != null)
		    neibs.add(dp[i - 1][j][kk]);
	    }
	    if (j > 0) {
		// left:
		if (dp[i][j - 1][kk] != null)
		    neibs.add(dp[i][j - 1][kk]);
	    }
	    if (i < nrows - 1) {
		// bottom:
		if (dp[i + 1][j][kk] != null)
		    neibs.add(dp[i + 1][j][kk]);
	    }
	    if (j < ncols - 1) {
		// right:
		if (dp[i][j + 1][kk] != null)
		    neibs.add(dp[i][j + 1][kk]);
	    }
	    if (neibs.isEmpty())
		continue;

	    int steps = Collections.min(neibs);

	    if (grid[i][j] == 1) {
		if (kk < kb) {
		    updated = dp[i][j][kk + 1] == null || dp[i][j][kk + 1] != steps + 1 ? 1 : 0;
		    dp[i][j][kk + 1] = steps + 1;
		}
	    } else {
		updated = dp[i][j][kk] == null || dp[i][j][kk] != steps + 1 ? 1 : 0;
		dp[i][j][kk] = steps + 1;
	    }
	    neibs.clear();
	}
	return new int[] { grid[i][j] == 1 ? k + 1 : k, updated };
    }

    public static void main(String[] args) {
	new Solution().shortestPath(new int[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 }, { 0, 1, 1, 1 },
						  { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 }, { 0, 1, 1, 1 }, { 0, 0, 0, 0 } }, 1);
    }
}
