import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Simple BFS solution.
 * 
 * Why BFS guarantees that the first time the destination is reached we have the
 * shortest path? Because all other states in the queue have either equivalent
 * steps or steps with one step larger. During the BFS, we monotonically
 * increase the steps. So we don't even need to look up steps of visited ones.
 * 
 * HOWEVER, I think there is some theorem for correctness missing in this
 * problem. Informally, the theorem would state something like this: <code>
 * 
 *   If there exists a shortest feasible path from left top corner to the right bottom corner with 
 *   k obstacle eliminations, the choices made for eliminating which obstacles are irrelevant.
 * 
 * </code>
 *
 */
class Solution {

	class State {
		int i, j, k, s;

		State(int i, int j, int k, int s) {
			this.i = i;
			this.j = j;
			this.k = k;
			this.s = s;
		}

		public int hashCode() {
			return (i * 41 + j) * 41 * 41 + k;
		}

		public boolean equals(Object o) {
			if (o instanceof State) {
				State other = (Solution.State) o;

				return other.i == i && other.j == j && other.k == k;
			}
			return false;
		}
	}

	public int shortestPath(int[][] grid, int k) {
		final int nrows = grid.length;
		final int ncols = grid[0].length;
		Map<State, State> visited = new HashMap<>();
		LinkedList<State> stack = new LinkedList<>();
		State s = new State(0, 0, k, 0);

		stack.add(s);
		visited.put(s, s);
		while (!stack.isEmpty()) {
			s = stack.removeFirst();
			if (s.i == nrows - 1 && s.j == ncols - 1)
				return s.s;

			List<State> nexts = new LinkedList<>();

			if (s.i < nrows - 1)
				nexts.add(new State(s.i + 1, s.j, s.k, s.s + 1));
			if (s.j < ncols - 1)
				nexts.add(new State(s.i, s.j + 1, s.k, s.s + 1));
			if (s.i > 0)
				nexts.add(new State(s.i - 1, s.j, s.k, s.s + 1));
			if (s.j > 0)
				nexts.add(new State(s.i, s.j - 1, s.k, s.s + 1));
			for (State nxt : nexts) {
				if (grid[nxt.i][nxt.j] == 1)
					nxt.k--;
				if (nxt.k >= 0)
					if (visited.put(nxt, nxt) == null)
						stack.add(nxt);
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		new Solution().shortestPath(new int[][] { { 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 }, { 0, 1, 1, 1 },
				{ 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 }, { 0, 1, 1, 1 }, { 0, 0, 0, 0 } }, 1);
	}
}
