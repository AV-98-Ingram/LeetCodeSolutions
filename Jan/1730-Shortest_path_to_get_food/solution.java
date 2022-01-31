import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Solution {

    static final int[][] D = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public int getFood(char[][] grid) {
	int[] me = new int[2];
	final int nrows = grid.length;
	final int ncols = grid[0].length;

	for (int i = 0; i < nrows; i++)
	    for (int j = 0; j < ncols; j++)
		if (grid[i][j] == '*') {
		    me[0] = i;
		    me[1] = j;
		    break;
		}

	// BFS:
	LinkedList<int[]> queue = new LinkedList<>();
	Set<Integer> visited = new HashSet<>();

	queue.add(new int[] { me[0], me[1], 0 });
	while (!queue.isEmpty()) {
	    int[] cell = queue.removeFirst();

	    if (!visited.add(cell[0] * ncols + cell[1]))
		continue;
	    if (grid[cell[0]][cell[1]] == '#')
		return cell[2];
	    for (int[] d : D) {
		int x = cell[0] + d[0];
		int y = cell[1] + d[1];

		if (0 <= x && x < nrows && 0 <= y && y < ncols && grid[x][y] != 'X')
		    queue.add(new int[] { x, y, cell[2] + 1 });
	    }
	}
	return -1;
    }
}
