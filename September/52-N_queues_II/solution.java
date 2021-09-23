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
	}

	Set<Integer> setOfN = new HashSet<>();

	public int totalNQueens(int n) {
		for (int i = 0; i < n; i++)
			setOfN.add(i);

		int result = 0;

		for (Integer cand : placeNextRow(new State(n), n)) {
			State state = new State(n);

			state.place(0, cand);
			result += solve(state, n);
		}
		return result;
	}

	private int solve(State state, int n) {
	    int result = 0;
	    if (state.nplaced == n) {
		return 1;
	    }
	    for (Integer cand : placeNextRow(state, n)) {
		int row = state.nplaced;
		
		state.place(row, cand);
		result += solve(state, n);
		state.take(row);
	    }
	    return result;
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
}


