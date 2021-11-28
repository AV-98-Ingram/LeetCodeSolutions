class Solution {

	/*
	 * Idea: DP.
	 * 
	 * For any kind of line: horizontal, vertical, diagonal, or anti-diagonal,
	 * we can count the length either from left to right or from top to bottom.
	 * So we build our solution in such order: (0, 0), (1, 0), ..., (m-1, 0),
	 * (0, 1), (1, 1), ..., (m-1, 1), ... i.e., top down on cells and left to
	 * right on bars.
	 * 
	 * Then what do we build? we build the solution solu[nrows][ncols][nkinds],
	 * where
	 * 
	 * solu[i][j][k] represents the max length of the line of kind k that ends
	 * at cell (i, j) from left to right or top down.
	 * 
	 * The way build solu is simple. See code below.
	 */

	static final int H = 0; // horizontal
	static final int V = 1; // vertical
	static final int D = 2; // diagonal
	static final int A = 3; // anti-diagonal

	public int longestLine(int[][] mat) {
		final int nr = mat.length;
		final int nc = mat[0].length;
		Integer solu[][][] = new Integer[nr][nc][4];
		int max = 0;

		for (int j = 0; j < nc; j++)
			for (int i = 0; i < nr; i++) {
				if (mat[i][j] == 0)
					continue;
				// horizontal:
				if (j > 0 && solu[i][j - 1][H] != null)
					solu[i][j][H] = solu[i][j - 1][H] + 1;
				else
					solu[i][j][H] = 1;
				// vertical:
				if (i > 0 && solu[i - 1][j][V] != null)
					solu[i][j][V] = solu[i - 1][j][V] + 1;
				else
					solu[i][j][V] = 1;
				// diagonal:
				if (i > 0 && j > 0 && solu[i - 1][j - 1][D] != null)
					solu[i][j][D] = solu[i - 1][j - 1][D] + 1;
				else
					solu[i][j][D] = 1;
				// anti-diagonal:
				if (i < nr - 1 && j > 0 && solu[i + 1][j - 1][A] != null)
					solu[i][j][A] = solu[i + 1][j - 1][A] + 1;
				else
					solu[i][j][A] = 1;
				max = Math.max(Math.max(
						Math.max(Math.max(max, solu[i][j][H]), solu[i][j][V]),
						solu[i][j][D]), solu[i][j][A]);
			}
		return max;
	}

	public static void main(String[] args) {
		new Solution().longestLine(
				new int[][] { { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 1 } });
	}
}
