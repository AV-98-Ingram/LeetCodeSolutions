import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution2 {

	static final Integer[] nine = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	private class Region {
		int id;
		Set<Integer> region = new HashSet<>(Arrays.asList(nine));

		Region(int id) {
			this.id = id;
		}
	}

	// state[i]: available numbers in the i-th region
	Region[] state = new Region[27];

	private Set<Integer> next(int i) {
		return state[i].region;
	}

	private boolean fill(int i, int val) {
		return state[i].region.remove(val);
	}

	private boolean erase(int i, int val) {
		return state[i].region.add(val);
	}

	public void solveSudoku(char[][] board) {
		for (int i = 0; i < 27; i++)
			state[i] = new Region(i);
		// init:
		for (int r = 0; r < 9; r++)
			for (int c = 0; c < 9; c++) {
				if (board[r][c] == '.')
					continue;

				int sub_idx = (r / 3) * 3 + (c / 3);
				int val = board[r][c] - '0';

				fill(r, val);
				fill(c + 9, val);
				fill(sub_idx + 18, val);
			}

		solve(board, null, 0);
	}

	private int getNonEmptyMin(Region[] state) {
		int min = 9;
		int idx = -1;

		for (int i = 0; i < state.length; i++) {
			if (!state[i].region.isEmpty())
				if (state[i].region.size() < min) {
					min = state[i].region.size();
					idx = i;
				}
		}
		return idx;
	}

	private boolean solve(char[][] board, Region region, int relative) {
		if (region == null) {
			int min = getNonEmptyMin(state);

			if (min < 0)
				return true;
			region = state[min];
		}

		int r, c, sub_idx;

		do {
			if (region.id < 9) {
				r = region.id;
				c = relative;
				sub_idx = (r / 3) * 3 + c / 3;
			} else if (region.id < 18) {
				r = relative;
				c = region.id - 9;
				sub_idx = (r / 3) * 3 + c / 3;
			} else {
				sub_idx = region.id - 18;
				r = (sub_idx / 3) * 3 + relative / 3;
				c = (sub_idx % 3) * 3 + relative % 3;
			}
			relative++;
		} while (board[r][c] != '.');

		Set<Integer> cands = new HashSet<>(next(r));

		cands.retainAll(next(c + 9));
		cands.retainAll(next(sub_idx + 18));
		for (int cand : cands) {
			fill(r, cand);
			fill(c + 9, cand);
			fill(sub_idx + 18, cand);
			board[r][c] = (char) (cand + (int) '0');

			if (region.region.isEmpty()) {
				if (solve(board, null, 0))
					return true;
			} else {
				if (solve(board, region, relative))
					return true;
			}
			// erase if failed:
			erase(r, cand);
			erase(c + 9, cand);
			erase(sub_idx + 18, cand);
			board[r][c] = '.';
			continue;
		}
		return false;
	}

	public static void main(String[] args) {
		char[][] board = new char[][] {
				{ '.', '.', '9', '7', '4', '8', '.', '.', '.' },
				{ '7', '.', '.', '.', '.', '.', '.', '.', '.' },
				{ '.', '2', '.', '1', '.', '9', '.', '.', '.' },
				{ '.', '.', '7', '.', '.', '.', '2', '4', '.' },
				{ '.', '6', '4', '.', '1', '.', '5', '9', '.' },
				{ '.', '9', '8', '.', '.', '.', '3', '.', '.' },
				{ '.', '.', '.', '8', '.', '3', '.', '2', '.' },
				{ '.', '.', '.', '.', '.', '.', '.', '.', '6' },
				{ '.', '.', '.', '2', '7', '5', '9', '.', '.' } };

		new Solution2().solveSudoku(board);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.printf("%2d", (int) (board[i][j] - '0'));
			}
			System.out.println();
		}
	}
}
