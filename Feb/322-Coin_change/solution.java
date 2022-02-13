class Solution {
    
    public int coinChange(int[] coins, int amount) {
	int dp[] = new int[amount + 1];

	dp[0] = 0;
	for (int i = 1; i <= amount; i++) {
	    dp[i] = -1;
	    for (int coin : coins) {
		if (i-coin < 0)
		    continue;
		if (dp[i-coin] < 0)
		    continue;
		if (dp[i] < 0)
		    dp[i] = dp[i-coin] + 1;
		else
		    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
	    }
	}
	return dp[amount];
    }
    
}
