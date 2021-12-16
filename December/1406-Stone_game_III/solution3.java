class Solution {

    int[] dp; // dp[pos]: the player if start his/her turn at position
	      // pos, the biggest advantage he/she can get from this
	      // turn.

    public String stoneGameIII(int[] stoneValue) {
	dp = new int[stoneValue.length];
	
	final int len = stoneValue.length;
	
	dp[len - 1] = stoneValue[len - 1];
	for (int i = len - 2; i >= 0; i--) {
	    int scoreThisTurn = stoneValue[i];
	    int maxAdvantage = scoreThisTurn - dp[i + 1];
	    
	    for (int j = 1; j < 3 && i + j < len; j++) {
		scoreThisTurn += stoneValue[i + j];
		
		int advantage = scoreThisTurn;
		
		if (i + j + 1 < len)
		    advantage -= dp[i + j + 1];
		if (maxAdvantage < advantage)
		    maxAdvantage = advantage;
		dp[i] = maxAdvantage;
	    }
	}
	if (dp[0] > 0)
	    return "Alice";
	else if (dp[0] < 0)
	    return "Bob";
	return "Tie";
    }

    public static void main(String[] args) {
	new Solution().stoneGameIII(new int[] { 1, 2, 3, 6 });
    }
}
