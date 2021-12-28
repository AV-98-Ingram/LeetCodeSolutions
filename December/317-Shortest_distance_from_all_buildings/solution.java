import java.util.ArrayList;
import java.util.LinkedList;

/**
   BFS from houses to empty cells.  
   Use a counter table to keep track of how many houses have reached an empty cell.
   Also use a distance table to track total travel distance from searched houses to an empty cell.

   Optimization:
   1. Use the counter table to quickly give up empty cells that cannot reach all houses
   2. Use the counter table to recognize visited cells during each BFS.
 */
class Solution {
    class Cell {
	int x, y;

	Cell(int x, int y) {
	    this.x = x;
	    this.y = y;
	}

	public boolean equals(Object o) {
	    if (o instanceof Cell) {
		Cell other = (Solution.Cell) o;

		return other.x == x && other.y == y;
	    }
	    return false;
	}

	public int hashCode() {
	    return (x << 6) + y;
	}
    }

    int nrows, ncols;

    public int shortestDistance(int[][] grid) {
	nrows = grid.length;
	ncols = grid[0].length;

	ArrayList<int[]> houses = new ArrayList<>();

	// assign all houses ID:
	for (int i = 0; i < nrows; i++)
	    for (int j = 0; j < ncols; j++) {
		if (grid[i][j] == 1)
		    houses.add(new int[] { i, j });
	    }

	int[][] dists = new int[nrows][ncols];
	int[][] counts = new int[nrows][ncols];
	int min = -1;
	int id = 0;

	for (int[] house : houses)
	    min = bfs(house, grid, dists, counts, id++, houses.size());
	return min;
    }

    private int bfs(int[] house, int[][] grid, int[][] dists, int[][] counts, int houseID, int nhouses) {
	LinkedList<Cell> que = new LinkedList<>();
	LinkedList<Cell> nexts = new LinkedList<>();
	int min = -1;
	int counter = 1;

	if (house[0] > 0)
	    que.add(new Cell(house[0] - 1, house[1]));
	if (house[0] < nrows - 1)
	    que.add(new Cell(house[0] + 1, house[1]));
	if (house[1] > 0)
	    que.add(new Cell(house[0], house[1] - 1));
	if (house[1] < ncols - 1)
	    que.add(new Cell(house[0], house[1] + 1));
	while (!que.isEmpty()) {
	    while (!que.isEmpty()) {
		Cell c = que.removeFirst();

		if (grid[c.x][c.y] != 0)
		    continue;
		if (counts[c.x][c.y] == houseID + 1)
		    continue; // visited
		if (counts[c.x][c.y] < houseID)
		    continue; // unreachable cell
		if (grid[c.x][c.y] == 0) {
		    counts[c.x][c.y]++;
		    dists[c.x][c.y] += counter;
		    if (counts[c.x][c.y] == nhouses)
			if (min < 0)
			    min = dists[c.x][c.y];
			else
			    min = Math.min(min, dists[c.x][c.y]);
		}
		if (c.x > 0)
		    nexts.add(new Cell(c.x - 1, c.y));
		if (c.x < nrows - 1)
		    nexts.add(new Cell(c.x + 1, c.y));
		if (c.y > 0)
		    nexts.add(new Cell(c.x, c.y - 1));
		if (c.y < ncols - 1)
		    nexts.add(new Cell(c.x, c.y + 1));
	    }
	    counter++;

	    LinkedList<Cell> tmp = que;

	    que = nexts;
	    nexts = tmp;
	}
	return min;
    }

    public static void main(String[] args) {
	new Solution().shortestDistance(new int[][] { { 1, 0, 2, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 } });
    }
}
