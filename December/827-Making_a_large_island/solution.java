import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

    class DS {
	int id;
	int size;
	DS root;

	DS(int id) {
	    this.id = id;
	    this.size = 1;
	}

	DS union(DS other) {
	    DS myRoot = getRoot();
	    DS otherRoot = other.getRoot();

	    if (myRoot.id == otherRoot.id)
		return this;
	    if (myRoot.size > otherRoot.size) {
		otherRoot.root = myRoot;
		myRoot.size += otherRoot.size;
		return myRoot;
	    } else {
		myRoot.root = otherRoot;
		otherRoot.size += myRoot.size;
		return otherRoot;
	    }
	}

	DS getRoot() {
	    if (this.root == null)
		return this;

	    DS myRoot = this.root.getRoot();

	    this.root = myRoot;
	    return myRoot;
	}
    }

    public int largestIsland(int[][] grid) {
	final int nrows = grid.length;
	final int ncols = grid[0].length;
	DS[][] dss = new DS[nrows][ncols];
	int idc = 0;
	int max = 0; // final result
	Map<Integer, int[]> zeroes = new HashMap<>();

	for (int i = 0; i < nrows; i++)
	    for (int j = 0; j < ncols; j++) {
		if (grid[i][j] != 1)
		    continue;

		DS ds = new DS(idc++);

		dss[i][j] = ds;
		if (i > 0 && dss[i - 1][j] != null)
		    dss[i - 1][j].union(ds);
		if (j > 0 && dss[i][j - 1] != null)
		    dss[i][j - 1].union(ds);
		max = Math.max(max, ds.getRoot().size);
		if (i > 0 && grid[i - 1][j] == 0)
		    zeroes.putIfAbsent((i - 1) * ncols + j, new int[] { i - 1, j });
		if (j > 0 && grid[i][j - 1] == 0)
		    zeroes.putIfAbsent(i * ncols + j - 1, new int[] { i, j - 1 });
		if (i < nrows - 1 && grid[i + 1][j] == 0)
		    zeroes.putIfAbsent((i + 1) * ncols + j, new int[] { i + 1, j });
		if (j < ncols - 1 && grid[i][j + 1] == 0)
		    zeroes.putIfAbsent(i * ncols + j + 1, new int[] { i, j + 1 });
	    }
	if (idc == 0)
	    return 1;
	for (int[] zero : zeroes.values()) {
	    int i = zero[0], j = zero[1];
	    int size = 0;
	    Set<Integer> islands = new HashSet<>();

	    if (i > 0 && dss[i - 1][j] != null) {
		DS root = dss[i - 1][j].getRoot();

		if (islands.add(root.id))
		    size += root.size;
	    }
	    if (j > 0 && dss[i][j - 1] != null) {
		DS root = dss[i][j - 1].getRoot();

		if (islands.add(root.id))
		    size += root.size;
	    }
	    if (i < nrows - 1 && dss[i + 1][j] != null) {
		DS root = dss[i + 1][j].getRoot();

		if (islands.add(root.id))
		    size += root.size;
	    }
	    if (j < ncols - 1 && dss[i][j + 1] != null) {
		DS root = dss[i][j + 1].getRoot();

		if (islands.add(root.id))
		    size += root.size;
	    }
	    max = Math.max(max, size + 1);
	}
	return max;
    }
}
