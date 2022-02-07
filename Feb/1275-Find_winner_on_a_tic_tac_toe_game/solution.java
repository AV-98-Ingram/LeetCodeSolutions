class Solution {
    public String tictactoe(int[][] moves) {
        int rows[][] = new int[2][3];  
        int cols[][] = new int[2][3];
	int diags[][] = new int[2][2]; // diag and reverse diag
	int player = 0;// A first
	
	for (int[] move : moves) {
	    boolean win = ++rows[player][move[0]] == 3;

	    win |= ++cols[player][move[1]] == 3;
	    if (move[0] == move[1] && move[0] == 1) {
		win |= ++diags[player][0] == 3;
		win |= ++diags[player][1] == 3;
	    } else if (move[0] == move[1])
		win |= ++diags[player][0] == 3;
	    else if (move[0] == 2-move[1])
		win |= ++diags[player][1] == 3;

	    if (win)
		return player == 0 ? "A" : "B";
	    player = 1 - player;
	}
	if (moves.length == 9)
	    return "Draw";
	return "Pending";
    }
}
