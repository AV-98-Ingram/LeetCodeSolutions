import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

	class Cell {
		int x, y;

		Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int hashCode() {
			return x * 101 + y;
		}

		public boolean equals(Object o) {
			if (o instanceof Cell) {
				Cell other = (Cell) o;

				return other.x == x && other.y == y;
			}
			return false;
		}

		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}

	Map<Cell, Cell> edges = new HashMap<>();

	/**
	 * Idea: First connecting all cells into separate groups. Then the group
	 * containing (0,0) forms the first layer of candidates which takes 0 cost. Then
	 * performing topological BFS, i.e., <code>
	 *   1. first traverse all members in the current layer and see if target can be found; 
	 *   2. form the next layer with one more cost by changing arrows of all members of current layer;
	 *   3. go back to 1 then.
	 *   4. Note that we shall skip visited cells at each step.
	 * </code>
	 */
	public int minCost(int[][] grid) {
		final int nrows = grid.length;
		final int ncols = grid[0].length;

		for (int i = 0; i < nrows; i++)
			for (int j = 0; j < ncols; j++) {
				Cell src = new Cell(i, j);
				Cell dest = getDest(i, nrows, j, ncols, grid[i][j]);

				if (dest != null)
					edges.put(src, dest);
			}
		// BFS:
		LinkedList<Cell> queue = new LinkedList<>();
		Set<Cell> visited = new HashSet<>();
		int cost = 0;

		// invariant of queue: all elements in queue are reachable from (0,0) with a
		// same cost:
		queue.add(new Cell(0, 0));
		visited.add(new Cell(0, 0));
		while (!queue.isEmpty()) {
			List<Cell> level = new LinkedList<>(); // elements reachable with same cost

			while (!queue.isEmpty()) {
				Cell c = queue.removeFirst();

				do {
					if (c.x == nrows - 1 && c.y == ncols - 1)
						return cost;
					level.add(c);
					c = edges.get(c);
				} while (c != null && visited.add(c));
			}
			for (Cell cc : level)
				queue.addAll(neighbors(cc.x, nrows, cc.y, ncols, visited));
			cost++;
		}
		return cost;
	}

	private Cell getDest(int i, final int nrows, int j, final int ncols, int arrow) {
		if (arrow == 1 && j < ncols - 1)
			return new Cell(i, j + 1);
		if (arrow == 2 && j > 0)
			return new Cell(i, j - 1);
		if (arrow == 3 && i < nrows - 1)
			return new Cell(i + 1, j);
		if (arrow == 4 && i > 0)
			return new Cell(i - 1, j);
		return null;
	}

	private List<Cell> neighbors(int i, final int nrows, int j, final int ncols, Set<Cell> visited) {
		List<Cell> result = new LinkedList<>();
		Cell c;

		if (i > 0) {
			c = new Cell(i - 1, j);
			if (visited.add(c))
				result.add(c);
		}
		if (j > 0) {
			c = new Cell(i, j - 1);
			if (visited.add(c))
				result.add(c);
		}
		if (i < nrows - 1) {
			c = new Cell(i + 1, j);
			if (visited.add(c))
				result.add(c);
		}
		if (j < ncols - 1) {
			c = new Cell(i, j + 1);
			if (visited.add(c))
				result.add(c);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.print(
				new Solution().minCost(new int[][] { { 1, 1, 1, 1 }, { 2, 2, 2, 2 }, { 1, 1, 1, 1 }, { 2, 2, 2, 2 } }));

	}
}
