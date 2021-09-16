class Solution {

    boolean[][] table = new boolean[27][9];
    
    public boolean isValidSudoku(char[][] board) {
	for (int i = 0; i < 27; i++)
	    Arrays.fill(table[i], false);	
	for (int r = 0; r < 9; r++)
	    for (int c = 0; c < 9; c++) {
		int val = board[r][c] == '.' ? 0 : board[r][c] - '0';
		
		if (!update(r, c, val))
		    return false;
	    }
	return true;
    }

    private boolean[] ROW(int i) {return table[i];}    
    private boolean[] COL(int i) {return table[9+i];}
    private boolean[] SUB(int i) {return table[18+i];}

    boolean update(int r, int c, int val) {
	if (val == 0)
	    return true;
	val--; // decr for 0-based indexing
	if (ROW(r)[val])
	    // val is duplicated in row r
	    return false;
	ROW(r)[val] = true;
	if (COL(c)[val])
	    // val is duplicated in col c
	    return false;
	COL(c)[val] = true;
	
	int sub_idx = (r / 3) * 3 + c / 3;
	
	if (SUB(sub_idx)[val])
	    return false;
	SUB(sub_idx)[val] = true;
	return true;
    }

}
