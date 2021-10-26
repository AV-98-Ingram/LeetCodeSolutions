import java.util.HashSet;
import java.util.Set;

class Solution {

    class Pair {
	int x, y;

	Pair() {
	}

	Pair(int x, int y) {
	    this.x = x;
	    this.y = y;
	}

	public int hashCode() {
	    return x * 31 + y;
	}

	public boolean equals(Object o) {
	    if (o instanceof Pair) {
		Pair other = (Solution.Pair) o;

		return other.x == x && other.y == y;
	    }
	    return false;
	}
    }

    Set<Pair> unflippables = new HashSet<>();
    int xb, yb;
    char[][] board;

    public void solve(char[][] board) {
	xb = board.length;
	yb = board[0].length;
	this.board = board;
	for (int i = 0; i < xb; i++) {
	    if (board[i][0] == 'O')
		ppg(i, 0);
	    if (board[i][yb - 1] == 'O')
		ppg(i, yb - 1);
	}
	for (int j = 1; j < yb - 1; j++) {
	    if (board[0][j] == 'O')
		ppg(0, j);
	    if (board[xb - 1][j] == 'O')
		ppg(xb - 1, j);
	}

	// flip 'O' -> 'X'
	Pair tester = new Pair();

	for (int i = 1; i < xb - 1; i++)
	    for (int j = 1; j < yb - 1; j++) {
		if (board[i][j] == 'X')
		    continue;
		tester.x = i;
		tester.y = j;
		if (unflippables.contains(tester))
		    continue;
		board[i][j] = 'X';
	    }
    }

    // propagate:
    private void ppg(int x, int y) {
	if (unflippables.add(new Pair(x, y))) {
	    if (x + 1 < xb)
		if (board[x + 1][y] == 'O')
		    ppg(x + 1, y);
	    if (x - 1 >= 0)
		if (board[x - 1][y] == 'O')
		    ppg(x - 1, y);
	    if (y + 1 < yb)
		if (board[x][y + 1] == 'O')
		    ppg(x, y + 1);
	    if (y - 1 >= 0)
		if (board[x][y - 1] == 'O')
		    ppg(x, y - 1);
	}
    }

    public static void main(String[] args) {
	new Solution().solve(new char[][] { { 'O', 'X', 'O' },
					    { 'X', 'O', 'X' }, { 'O', 'X', 'O' } });
    }
}
