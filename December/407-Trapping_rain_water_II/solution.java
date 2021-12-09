import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
	/**
	 * Idea:  Too slow see solution2.java
	 * 
	 * Put all inner cells into a PriorityQueue where cells are ordered by their
	 * height. Start BFS from the lowest cell (obtainted from the queue). During the
	 * BFS from the cell c, also keep track of the lowest bar. If the cells reached
	 * by BFS from c can form a trap, we know that we can add water into it to the
	 * height of the lowest bar. Then we add the water and put these cells back to
	 * the queue. Otherwise, we poll another cell from the queue can repeat the
	 * process until the queue becomes empty.
	 */
	public int trapRainWater(int[][] heightMap) {
		PriorityQueue<Cell> queue = new PriorityQueue<>(
				(a, b) -> Integer.compare(heightMap[a.x][a.y], heightMap[b.x][b.y]));
		final int nrows = heightMap.length;
		final int ncols = heightMap[0].length;
		int result = 0;

		for (int i = 1; i < nrows - 1; i++)
			for (int j = 1; j < ncols - 1; j++)
				queue.add(new Cell(i, j));
		while (!queue.isEmpty()) {
			Cell c = queue.poll();
			Set<Cell> visited = new HashSet<>();
			LinkedList<Cell> l = new LinkedList<>();
			int bar = -1;

			l.add(c);
			while (!l.isEmpty()) {
				c = l.removeFirst();
				if (!visited.add(c))
					continue;

				List<Cell> nexts = new LinkedList<>();

				bar = getNexts(c, heightMap, nrows, ncols, nexts, bar, visited);
				if (bar >= 0)
					l.addAll(nexts);
			}
			if (bar > heightMap[c.x][c.y]) {
				// found a trap:
				for (Cell v : visited) {
					if (!queue.isEmpty() && queue.peek().equals(v))
						queue.poll();
					result += bar - heightMap[v.x][v.y];
					heightMap[v.x][v.y] = bar;
				}
				queue.add(c);
			}
		}
		return result;
	}

	private int getNexts(Cell c, int[][] map, int nrows, int ncols, List<Cell> out, int bar, Set<Cell> visited) {
		int[][] nxts = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int ch = map[c.x][c.y];

		for (int[] nxt : nxts)
			out.add(new Cell(c.x + nxt[0], c.y + nxt[1]));

		Iterator<Cell> iter = out.iterator();

		while (iter.hasNext()) {
			Cell neib = iter.next();

			if (visited.contains(neib)) {
				iter.remove();
				continue;
			}
			if (map[neib.x][neib.y] > ch) {
				iter.remove();
				if (bar != -1)
					bar = Math.min(bar, map[neib.x][neib.y]);
				else
					bar = map[neib.x][neib.y];
				continue;
			} else if (map[neib.x][neib.y] == ch
					&& !((neib.x == 0 || neib.y == 0 || neib.x == nrows - 1 || neib.y == ncols - 1)))
				continue;
			bar = -2; // bar == -2 means fail; bar == -1 means uninitialized
		}
		return bar;
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
		System.out.println(new Solution().trapRainWater(new int[][] { { 12, 13, 1, 12 }, { 13, 4, 13, 12 },
				{ 13, 8, 10, 12 }, { 12, 13, 12, 12 }, { 13, 13, 13, 13 } }));
	}
}
