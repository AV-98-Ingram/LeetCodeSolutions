class Solution {

    class Island {
	int id;
	Island root;

	Island(int id) {
	    this.id = id;
	    this.root = null;
	}

	void mergeTo(Island root) {
	    this.getRoot().root = root;
	}

	int getID() {
	    if (this.root != null)
		return this.root.getID();
	    return id;
	}

	Island getRoot() {
	    if (root == null)
		return this;
	    else
		return root.getRoot();
	}
    }

    public int numIslands(char[][] grid) {
	final int nrows = grid.length;
	final int ncols = grid[0].length;
	Island islands[][] = new Island[nrows][ncols];
	int n = 0; // island ID counter
	int m = 0; // number of merges

	for (int i = 0; i < nrows; i++)
	    for (int j = 0; j < ncols; j++) {
		if (grid[i][j] == '0') {
		    islands[i][j] = null;
		    continue;
		}
		if (i > 0 && grid[i - 1][j] == '1')
		    islands[i][j] = islands[i - 1][j];
		if (j > 0 && grid[i][j - 1] == '1') {
		    if (islands[i][j] == null)
			islands[i][j] = islands[i][j - 1];
		    else if (islands[i][j - 1].getID() != islands[i][j]
			     .getID()) {
			islands[i][j - 1].mergeTo(islands[i][j]);
			m++;
		    }
		}
		if (islands[i][j] == null)
		    islands[i][j] = new Island(++n);
	    }
	return n - m;

    }
}
