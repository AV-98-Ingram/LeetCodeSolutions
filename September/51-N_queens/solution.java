import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Solution {
	private class State {
		int[] posByRow;
		int nplaced;

		State(int n) {
			posByRow = new int[n];
			Arrays.fill(posByRow, -1);
			nplaced = 0;
		}

		void place(int row, int col) {
			this.posByRow[row] = col;
			this.nplaced++;
		}

		void take(int row) {
			this.posByRow[row] = -1;
			this.nplaced--;
		}

		List<String> print() {
			List<String> result = new LinkedList<>();

			for (int col : posByRow) {
				String s = "";

				for (int i = 0; i < posByRow.length; i++)
					if (i != col)
						s += ".";
					else
						s += "Q";
				result.add(s);
			}
			return result;
		}
	}

	Set<Integer> setOfN = new HashSet<>();

	public List<List<String>> solveNQueens(int n) {
		for (int i = 0; i < n; i++)
			setOfN.add(i);

		List<List<String>> result = new LinkedList<>();

		for (Integer cand : placeNextRow(new State(n), n)) {
			State state = new State(n);

			state.place(0, cand);
			solve(state, n, result);
		}
		return result;
	}

	private void solve(State state, int n, List<List<String>> result) {
		if (state.nplaced == n) {
			result.add(state.print());
			return;
		}
		for (Integer cand : placeNextRow(state, n)) {
			int row = state.nplaced;

			state.place(row, cand);
			solve(state, n, result);
			state.take(row);
		}
		return;
	}

	private Iterable<Integer> placeNextRow(State state, int n) {
		Set<Integer> candidates = new HashSet<>(setOfN);
		int row = state.nplaced;

		for (int i = 0; i < row; i++) {
			int placed = state.posByRow[i];

			candidates.remove(placed);
			candidates.remove(placed - (row - i));
			candidates.remove(placed + (row - i));
		}
		return candidates;
	}

	public static void main(String[] args) {
		for (List<String> strs : new Solution().solveNQueens(4)) {
			System.out.println(strs);
		}
	}
}
