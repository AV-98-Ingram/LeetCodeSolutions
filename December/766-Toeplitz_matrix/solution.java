class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
	final int nrows = matrix.length;
	final int ncols = matrix[0].length;

	for (int i = 0; i < nrows; i++) {
	    int r = i, c = 0;
	    int v = matrix[r++][c++];
	    
	    while (r < nrows && c < ncols)
		if (matrix[r++][c++] != v)
		    return false;
	}
	for (int i = 0; i < ncols; i++) {
	    int r = 0, c = i;
	    int v = matrix[r++][c++];
	    
	    while (r < nrows && c < ncols)
		if (matrix[r++][c++] != v)
		    return false;
	}
	return true;
    }
}
