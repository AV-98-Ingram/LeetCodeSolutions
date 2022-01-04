class TicTacToe {

    int[][] rows, cols;
    int[] diag, adiag;
    int n;
    
    public TicTacToe(int n) {
	rows = new int[n][2];
	cols = new int[n][2];
	diag = new int[2];
	adiag = new int[2];
	this.n = n;
    }
    
    public int move(int row, int col, int player) {
	if (++rows[row][player-1] == n)
	    return player;
	if (++cols[col][player-1] == n)
	    return player;
	if (row == col)
	    if (++diag[player-1] == n)
		return player;
	if (row == n - col - 1)
	    if (++adiag[player-1] == n)
		return player;
	return 0;
    }
}

