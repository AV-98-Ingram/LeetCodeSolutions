class Solution {
    /*
     * Each player plays optimal, i.e., each player at each step tries out all the
     * possibilities and pick the best one. This is the brutal force solution, which
     * is too slow.
     */
    public String stoneGameIII(int[] stoneValue) {
	int[] r = play(stoneValue, 0, 0, 0, true);

	if (r[0] > r[1])
	    return "Alice";
	else if (r[0] < r[1])
	    return "Bob";
	return "Tie";
    }

    private int[] play(int[] stones, int pos, int alice, int bob, boolean aliceTurn) {
	if (pos == stones.length)
	    return new int[]{alice, bob};

	int[] result = null;
	int add = 0;
	boolean everTied = false;
	int[] tieResult = null;
	    
	for (int i = 0; i < 3 && pos + i < stones.length; i++) {
	    add += stones[pos + i];
	    result = play(stones, pos + 1 + i, aliceTurn ? alice + add : alice, aliceTurn ? bob : bob + add,
			  !aliceTurn);
	    if (aliceTurn && result[0] > result[1])
		return result;
	    if (!aliceTurn && result[0] < result[1])
		return result;
	    if (result[0] == result[1]) {
		everTied = true;
		tieResult = result;
	    }
	}
	return everTied ? tieResult : result;
    }

    public static void main(String[] args) {
	new Solution().stoneGameIII(new int[] { 1, 2, 3, 6 });
    }
}
