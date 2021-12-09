class Solution {
    public int countSquares(int[][] matrix) {
	final int nrows = matrix.length;
	final int ncols = matrix[0].length;
	Integer dp[][] = new Integer[nrows][ncols];
	int result = 0;

	for (int i = 0; i < nrows; i++)
	    for (int j = 0; j < ncols; j++) {
		if (matrix[i][j] == 1) {
		    result += compute(i, j, matrix, dp, nrows, ncols);
		}
	    }
	return result;
    }

    private int compute(int i, int j, int[][] mat, Integer[][] dp, final int nrows, final int ncols) {
	final int ub = Math.min(nrows - i, ncols - j); // size upper bound
	int nmats = 0; // #(newly found matrixes)
	int n; // current size
	if (dp[i][j] == null) {
	    n = 1;
	    dp[i][j] = 1;
	    nmats = 1;
	} else
	    n = dp[i][j];

	int size;
	for (size = n; size < ub; size++) {
	    int k;
	    for (k = 0; k <= size; k++) {
		if (mat[i + k][j + size] != 1 || mat[i + size][j + k] != 1)
		    break;
	    }
	    if (k <= size)
		break; // failed at size
	}
	if (size > n) {
	    // update dp and nmats:
	    for (int k = i; k < i + size; k++)
		for (int l = j; l < j + size; l++) {
		    if (dp[k][l] != null) {
			int old = dp[k][l];

			dp[k][l] = size - Math.max(k - i, l - j);
			nmats += dp[k][l] - old;
		    } else {
			dp[k][l] = size - Math.max(k - i, l - j);
			nmats += dp[k][l];
		    }
		}
	}
	return nmats;
    }
}
