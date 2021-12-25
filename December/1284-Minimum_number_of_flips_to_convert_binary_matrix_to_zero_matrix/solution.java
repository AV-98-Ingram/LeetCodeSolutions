import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    class State {
	int[][] mat;

	State(int[][] mat) {
	    this.mat = new int[mat.length][];
	    int i = 0;
	    for (int[] row : mat)
		this.mat[i++] = Arrays.copyOf(row, row.length);
	}

	public int hashCode() {
	    int hashCode = 0;

	    for (int[] row : mat) {
		int tmp = Arrays.hashCode(row);

		hashCode = hashCode * 31 + tmp;
	    }
	    return hashCode;
	}

	public boolean equals(Object o) {
	    if (o instanceof State) {
		State other = (State) o;
		final int nrows = mat.length;

		for (int i = 0; i < nrows; i++)
		    if (!Arrays.equals(mat[i], other.mat[i]))
			return false;
		return true;
	    }
	    return false;
	}
    }

    public int minFlips(int[][] mat) {
	State init = new State(mat);
	Map<State, Integer> visited = new HashMap<>(1024);
	final int nrows = mat.length;
	final int ncols = mat[0].length;

	int result = dfs(init, visited, new HashSet<>(1024), nrows, ncols);

	if (result >= 1024) // at largest 3 x 3 matrix, no more than 2^9 states
	    return -1;
	return result;
    }

    private int dfs(State s, Map<State, Integer> visited, Set<State> onStack, final int nrows, final int ncols) {
	Integer knownDist = visited.get(s);

	if (knownDist != null)
	    return knownDist;
	if (isDone(s)) {
	    visited.put(s, 0);
	    return 0;
	}

	int minDist = 1024;
		
	onStack.add(s);
	for (State nxt : enable(s, nrows, ncols)) {
	    if (onStack.contains(nxt))
		continue;

	    int dist = dfs(nxt, visited, onStack, nrows, ncols);

	    minDist = Math.min(dist + 1, minDist);
	}
	onStack.remove(s);
	visited.put(s, minDist);
	return minDist;
    }

    // all 0s
    private boolean isDone(State s) {
	for (int[] row : s.mat) {
	    for (int c : row)
		if (c != 0)
		    return false;
	}
	return true;
    }

    // return all next states
    private List<State> enable(State s, final int nrows, final int ncols) {
	List<State> result = new LinkedList<>();

	for (int i = 0; i < nrows; i++)
	    for (int j = 0; j < ncols; j++) {
		State nxt = new State(s.mat);

		nxt.mat[i][j] = 1 - nxt.mat[i][j];
		if (i > 0)
		    nxt.mat[i - 1][j] = 1 - s.mat[i - 1][j];
		if (i < nrows - 1)
		    nxt.mat[i + 1][j] = 1 - s.mat[i + 1][j];
		if (j > 0)
		    nxt.mat[i][j - 1] = 1 - s.mat[i][j - 1];
		if (j < ncols - 1)
		    nxt.mat[i][j + 1] = 1 - s.mat[i][j + 1];
		result.add(nxt);
	    }
	return result;
    }

    public static void main(String[] args) {
	new Solution().minFlips(new int[][] { { 0, 0 }, { 0, 1 } });
    }
}
