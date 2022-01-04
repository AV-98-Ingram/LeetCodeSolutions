class TicTacToe {

    static int directs[][] = new int[][] { // directions
	{ 1, 0 }, // bot
	{ 0, 1 }, // right
	{ 1, 1 }, // right-bot
	{ -1, 1 }, // right-top
	{ 1, -1 }, // left-bot
	{ -1, -1 }, // left-top
	{ 0, -1 }, // left
	{ -1, 0 }, // top
    };

    private int n;
    private int[][] board;

    public TicTacToe(int n) {
	this.n = n;
	this.board = new int[n][n];
    }

    public int move(int row, int col, int player) {
	board[row][col] = player;
	for (int i = 0; i < 4; i++) {
	    int r = row + directs[i][0];
	    int c = col + directs[i][1];
	    int chain = 1;

	    chain += length(r, c, i, player);
	    r = row + directs[7 - i][0];
	    c = col + directs[7 - i][1];
	    chain += length(r, c, 7 - i, player);
	    if (chain == n)
		return player;
	}
	return 0;
    }

    private int length(int r, int c, int direct, int player) {
	if (0 <= r && 0 <= c && r < n && c < n) {
	    if (board[r][c] == player)
		return length(r + directs[direct][0], c + directs[direct][1], direct, player) + 1;
	}
	return 0;
    }
}
