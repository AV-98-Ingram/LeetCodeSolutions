class Solution {

    int nrows, ncols;
    
    public int[] findDiagonalOrder(int[][] mat) {
        nrows = mat.length;
	ncols = mat[0].length;
	
	int[] result = new int[nrows * ncols];

	goDiagonal(mat, 0, 0, true, result, 0);
	return result;
    }

    private boolean goDiagonal(int[][] mat, int r, int c, boolean goUp, int[] out, int outPos) {
	out[outPos] = mat[r][c];
	if (r == nrows-1 && c == ncols-1)	    
	    return true;		
	if (goUp) {
	    if (r - 1 >= 0 && c + 1 < ncols)
		return goDiagonal(mat, r-1, c+1, goUp, out, outPos+1);
	    if (c + 1 < ncols)
		return goDiagonal(mat, r, c+1, !goUp, out, outPos+1);
	    return goDiagonal(mat, r+1, c, !goUp, out, outPos+1);
	} else {
	    if (r + 1 < nrows && c-1 >= 0)
		return goDiagonal(mat, r+1, c-1, false, out, outPos+1);
	    if (r + 1 < nrows)
		return goDiagonal(mat, r+1, c, true, out, outPos+1);
	    return goDiagonal(mat, r, c+1, true, out, outPos+1);
	}	
    }
}
