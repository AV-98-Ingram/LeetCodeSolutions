import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

class Solution {

    class DS {
	private int id;
	private DS root;

	DS(int id) {
	    this.id = id;
	    this.root = null;
	}

	DS getRoot() {
	    if (root == null)
		return this;

	    DS myRoot = root.getRoot();

	    this.root = myRoot;
	    return myRoot;
	}

	void mergeTo(DS other) {
	    DS root = getRoot();

	    if (root.id == other.getRoot().id)
		return;
	    root.root = other;

	    // merge could cause the root being merged
	    // into to become non-closed:
	    if (root.id < 0) {
		DS otherRoot = other.getRoot();

		if (otherRoot.id > 0)
		    otherRoot.id = -otherRoot.id;
	    }
	}

	public String toString() {
	    return String.valueOf(getRoot().id);
	}
    }

    /**
     * Idea: Union cells of 0s (islands). Once a cell is connected to the edge, the
     * whole unioned group is NOT a closed island.
     */
    public int closedIsland(int[][] grid) {
	final int nrows = grid.length;
	final int ncols = grid[0].length;
	int idCounter = 1;
	Map<DS, DS> roots = new IdentityHashMap<>();
	DS[][] dss = new DS[nrows][ncols];

	for (int i = 0; i < nrows; i++)
	    for (int j = 0; j < ncols; j++) {
		if (grid[i][j] == 1)
		    continue;

		DS ds = new DS(idCounter++);
		DS top = i > 0 ? dss[i - 1][j] : null;
		DS left = j > 0 ? dss[i][j - 1] : null;

		if (top != null)
		    ds.mergeTo(top);
		if (left != null)
		    ds.mergeTo(left);
		dss[i][j] = ds;
		if (top == null && left == null)
		    roots.put(ds.getRoot(), ds.getRoot());
		if (i == 0 || j == 0 || i == nrows - 1 || j == ncols - 1) {
		    DS dsRoot = ds.getRoot();

		    if (dsRoot.id > 0)
			dsRoot.id = -dsRoot.id; // marks as non-closed
		}
	    }

	Set<Integer> closedIslands = new HashSet<>();

	for (DS root : roots.keySet())
	    if (root.getRoot().id > 0)
		closedIslands.add(root.getRoot().id);
	return closedIslands.size();
    }
}
