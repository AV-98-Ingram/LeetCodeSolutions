import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Solution {
    public int numberOfPatterns(int m, int n) {
	int sum = 0;

	sum += dfs(new int[] { 0, 0 }, m, n, new HashSet<>()) * 4;
	sum += dfs(new int[] { 0, 1 }, m, n, new HashSet<>()) * 4;
	sum += dfs(new int[] { 1, 1 }, m, n, new HashSet<>());
	return sum;
    }

    private int dfs(int[] cell, final int m, final int n, Set<Integer> visited) {
	// invariant: 'cell' not in 'visited'
	visited.add(cell[0] * 3 + cell[1]);

	int len = visited.size();
	int nvalids = 0;

	if (m <= len && len <= n)
	    nvalids = 1;
	if (len != n)
	    for (int[] nxt : nextCells(cell, visited))
		nvalids += dfs(nxt, m, n, visited);
	visited.remove(cell[0] * 3 + cell[1]);
	return nvalids;
    }

    static int[][] allCells = new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 },
					    { 2, 1 }, { 2, 2 }, };

    private Iterable<int[]> nextCells(int[] cell, Set<Integer> visited) {
	List<int[]> ret = new LinkedList<>();

	for (int[] nxt : allCells) {
	    if (visited.contains(nxt[0] * 3 + nxt[1]))
		continue;

	    if (nxt[0] == cell[0] && Math.abs(nxt[1] - cell[1]) == 2) {
		if (visited.contains(nxt[0] * 3 + 1))
		    ret.add(nxt);
	    } else if (nxt[1] == cell[1] && Math.abs(nxt[0] - cell[0]) == 2) {
		if (visited.contains(3 + nxt[1]))
		    ret.add(nxt);
	    } else if ((cell[0] == 0 || cell[0] == 2) && (cell[1] == 0 || cell[1] == 2)) {
		if ((nxt[0] == 0 || nxt[0] == 2) && (nxt[1] == 0 || nxt[1] == 2)) {
		    if (visited.contains(4))
			ret.add(nxt);
		} else
		    ret.add(nxt);
	    } else
		ret.add(nxt);
	}
	return ret;
    }

    public static void main(String args[]) {
	new Solution().numberOfPatterns(1, 9);
    }
}
