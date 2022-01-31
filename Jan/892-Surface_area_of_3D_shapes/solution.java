class Solution {

    static int[][] directs = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
    
    public int surfaceArea(int[][] grid) {
	final int nrows = grid.length;
	final int ncols = grid[0].length;
	int result = 0;
	
	for (int i = 0; i < nrows; i++)
	    for (int j = 0; j < ncols; j++) {
		int h = grid[i][j];

		if (h > 0)
		    result += 2; // top and bottom		
		for (int[] direct : directs) {
		    int neib_i = i + direct[0];
		    int neib_j = j + direct[1];		    

		    if (neib_i >= 0 && neib_i < nrows &&
			neib_j >= 0 && neib_j < ncols) {		    
			int diff = h - grid[neib_i][neib_j];
			
			if (diff > 0)
			    result += diff;
		    } else
			result += h;
		}
	    }
	return result;
    }
}
