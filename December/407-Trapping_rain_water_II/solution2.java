import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
	/**
	 * Idea: BFS
	 * 
	 * This idea is amazing to me. I cannot find any better one for two days.
	 * 
	 * First, the 4 outermost boundaries cannot hold water. Let's use a
	 * PriorityQueue (min heap) to maintain boundary cells. An invariant of the
	 * queue is that it always only contain boundary cells. After we put the
	 * outermost boundary cells into the queue we can start to do BFS.
	 * 
	 * The BFS starts from the top cell of the queue. Since cells in queue are
	 * boundaries, the cell itself cannot hold water. But since this cell is the
	 * lowest bar in boundaries, any inner cell that connects to it can hold water
	 * until at "bar" height. Inner neighbor cells are discovered through BFS from
	 * the top boundary cell. Once an inner cell gets water, we know the inner cell
	 * 'c' can no longer hold any more water as 'c' connects to a boundary cell,
	 * which is now same height as 'c'. So we can safely mark 'c' as visited and put
	 * it back to the queue---'c' now is a new boundary cell. If an inner neighbor
	 * cell is NOT lower than the bar, it just becomes a new boundary without
	 * holding water.
	 * 
	 * Note the invariant that all cells in the queue are "boundaries" (including
	 * "new boundaries") still holds. Then once we processed all 4 neighbors of the
	 * current top cell in queue, we can pop it and get the next top cell from the
	 * queue. At this point, we just reached the same state as the first time we
	 * popped the top from the queue: we start discover inner cells from the lowest
	 * bar (i.e., the new top cell) in the boundaries. So we just repeat the steps
	 * that have been done so far. The loop will end after all cells are visited.
	 *
	 * 
	 * The thing that got me confused at first is that why an inner neighbor of a
	 * boundary cell immediately becomes a new boundary cell after adding water (or
	 * not if it is higher than the bar). The explanation to it is that the inner
	 * neighbor is neighboring with the lowest bar of the boundary, so once the
	 * inner neighbor "becomes" (either after adding water or it originally is) no
	 * lower than the lowest bar, it can never hold any more water. A cell that can
	 * never hold any more water is a boundary cell. One can see the boundaries as
	 * not only the peripheral boundaries but also inner boundaries that divide the
	 * matrix into isolated pools.
	 * 
	 */
	public int trapRainWater(int[][] heightMap) {
		final int nrows = heightMap.length;
		final int ncols = heightMap[0].length;
		int result = 0;
		PriorityQueue<Cell> pq = new PriorityQueue<>(
				(a, b) -> Integer.compare(heightMap[a.x][a.y], heightMap[b.x][b.y]));

		for (int i = 1; i < nrows - 1; i++) {
			pq.add(new Cell(i, 0));
			pq.add(new Cell(i, ncols - 1));
		}
		for (int i = 1; i < ncols - 1; i++) {
			pq.add(new Cell(0, i));
			pq.add(new Cell(nrows - 1, i));
		}

		int bar = 0;
		Set<Cell> visited = new HashSet<>();

		while (!pq.isEmpty()) {
			Cell c = pq.remove();
			if (!visited.add(c))
				continue;
			bar = Math.max(bar, heightMap[c.x][c.y]);
			for (Cell neib : neighbors(c, nrows, ncols)) {
				if (visited.contains(neib))
					continue;
				// neib is guaranteed not on edges
				if (heightMap[neib.x][neib.y] < bar) {
					result += bar - heightMap[neib.x][neib.y];
					heightMap[neib.x][neib.y] = bar;
				}
				pq.add(neib);
			}
		}
		return result;
	}

	private Iterable<Cell> neighbors(Cell c, int nrows, int ncols) {
		List<Cell> neibs = new LinkedList<>();
		List<Cell> neibs2 = new LinkedList<>();

		neibs.add(new Cell(c.x - 1, c.y));
		neibs.add(new Cell(c.x + 1, c.y));
		neibs.add(new Cell(c.x, c.y - 1));
		neibs.add(new Cell(c.x, c.y + 1));
		for (Cell neib : neibs) {
			if (0 < neib.x && neib.x < nrows - 1 && 0 < neib.y && neib.y < ncols - 1)
				neibs2.add(neib);
		}
		return neibs2;
	}

	class Cell {
		int x, y;

		Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int hashCode() {
			return x * 201 + y;
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

	public static void main(String[] args) {
		System.out.println(new Solution().trapRainWater(new int[][] { { 8, 8, 8, 8, 8, 8, 8 }, { 8, 8, 8, 8, 8, 8, 8 },
				{ 8, 1, 2, 5, 4, 5, 5 }, { 8, 8, 8, 1, 5, 8, 8 }, { 8, 8, 8, 8, 8, 8, 8 } }));
	}
}
