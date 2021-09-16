import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {

	static final Integer[] nine = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	private class Region {
		Set<Integer> region = new HashSet<>(Arrays.asList(nine));
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
			state[i] = new Region();
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
		// solve
		solve(board, 0, 0);
	}

	private boolean solve(char[][] board, int r, int c) {
		// get to next empty cell:
		while (board[r][c] != '.') {
			if (r == 8 && c == 8)
				return true;
			if (c < 8)
				c++;
			else {
				r++;
				c = 0;
			}
		}

		Set<Integer> cands = new HashSet<>(next(r));
		int sub_idx = (r / 3) * 3 + (c / 3);

		cands.retainAll(next(c + 9));
		cands.retainAll(next(sub_idx + 18));
		for (int cand : cands) {
			fill(r, cand);
			fill(c + 9, cand);
			fill(sub_idx + 18, cand);
			board[r][c] = (char) (cand + (int) '0');
			if (solve(board, r, c))
				return true;
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

		new Solution().solveSudoku(board);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.printf("%2d", (int) (board[i][j] - '0'));
			}
			System.out.println();
		}
	}
}
