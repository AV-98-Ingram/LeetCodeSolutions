class Solution {

    Boolean cache[]; // can player win when facing n stones

    public boolean winnerSquareGame(int n) {
	cache = new Boolean[n + 1];
	return play(0, n) == 0;
    }

    private int play(int player, int n) {
	if (n == 0)
	    return (1 - player);

	if (cache[n] != null)
	    return cache[n] ? player : 1 - player;

	int pick = 1;
	int i = 1;
	int winner = 1 - player;

	while (pick <= n) {
	    if (play(1 - player, n - pick) == player) {
		winner = player;
		break;
	    }
	    i++;
	    pick = i * i;
	}
	cache[n] = winner == player;
	return winner;
    }

    public static void main(String[] args) {
	new Solution().winnerSquareGame(7);
    }
}
