import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

	class Cell {
		int r, c;

		Cell(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public boolean equals(Object o) {
			if (o instanceof Cell) {
				Cell other = ((Cell) o);

				return other.r == r && other.c == c;
			}
			return false;
		}

		public int hashCode() {
			return r * 201 + c;
		}

		public String toString() {
			return "(" + r + ", " + c + ")";
		}
	}

	static final int[][] neibs = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	static final List<Cell> emptyList = new LinkedList<>();

	public int longestIncreasingPath(int[][] matrix) {
		Map<Cell, List<Cell>> edges = new HashMap<>();
		final int nrows = matrix.length;
		final int ncols = matrix[0].length;
		Set<Cell> startSet = new HashSet<>();
		Set<Cell> nonStartSet = new HashSet<>();

		for (int i = 0; i < nrows; i++)
			for (int j = 0; j < ncols; j++) {
				int val = matrix[i][j];
				Cell src = new Cell(i, j);

				if (!nonStartSet.contains(src))
					startSet.add(src);
				for (int[] neib : neibs) {
					int neib_i = i + neib[0];
					int neib_j = j + neib[1];

					if (neib_i >= 0 && neib_i < nrows && neib_j >= 0 && neib_j < ncols
							&& val > matrix[neib_i][neib_j]) {
						Cell dest = new Cell(neib_i, neib_j);

						edges.computeIfAbsent(src, k -> new LinkedList<>()).add(dest);
						startSet.remove(dest);
						nonStartSet.add(dest);
					}
				}
			}
		// DFS with marking the longest path reaching a node:
		Map<Cell, Integer> map = new HashMap<>();
		int max = 0;

		for (Cell start : startSet) {
			max = Math.max(max, dfs(start, edges, map, 1));
		}
		return max;
	}

	private int dfs(Cell c, Map<Cell, List<Cell>> edges, Map<Cell, Integer> map, int len) {
		int oldLen = map.getOrDefault(c, 0);

		if (oldLen >= len)
			return 0;
		else
			map.put(c, len);

		List<Cell> nxts = edges.getOrDefault(c, emptyList);

		if (nxts.isEmpty())
			return len;

		int max = 0;

		for (Cell nxt : nxts)
			max = Math.max(max, dfs(nxt, edges, map, len + 1));
		return max;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().longestIncreasingPath(new int[][] { { 7, 7, 5 }, { 2, 4, 6 }, { 8, 2, 0 } }));
	}
}
