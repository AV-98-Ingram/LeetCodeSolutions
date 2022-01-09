import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Solution {
	public int snakesAndLadders(int[][] board) {
		final int n = board.length;
		final int goal = n * n;
		LinkedList<int[]> que = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();

		que.add(new int[] { 1, 0 });
		while (!que.isEmpty()) {
			int[] cell = que.removeFirst();

			if (!visited.add(cell[0]))
				continue;
			if (cell[0] == goal)
				return cell[1];

			int i = 1;

			while (i <= 6 && cell[0] + i <= goal) {
				int nxt = step(cell[0] + i, board, n);

				que.add(new int[] { nxt, cell[1] + 1 });
				i++;
			}
		}
		return -1;
	}

	private int step(int num, int[][] board, int n) {
		// decode num to the grid indices:
		int col = (num - 1) % n;
		int row = (num - 1) / n;

		if ((row & 1) == 1)
			col = n - 1 - col;
		row = n - 1 - row;
		// get next cell:
		if (board[row][col] == -1)
			return num;
		return board[row][col];
	}
}
