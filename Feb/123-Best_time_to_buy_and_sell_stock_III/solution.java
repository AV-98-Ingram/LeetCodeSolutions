class Solution {
    public int maxProfit(int[] prices) {
	final int len = prices.length;
	// min[i][j]: min price in prices[i .. j]
	int[][] min = new int[len][len];
	// dp[i][j]: max profit earned after sell at prices[j] after
	// i-th (0-indexed) transactions
	int[][] dp = new int[2][len];

	for (int i = 0; i < len; i++) {
	    min[i][i] = prices[i];
	    for (int j = i+1; j < len; j++)
		min[i][j] = Math.min(min[i][j-1], prices[j]);
	}

	int maxProfit = 0;

	for (int i = 0; i < len; i++)
	    dp[1][i] = prices[i] - min[0][i];
	for (int i = 0; i < len; i++) {
	    dp[2][i] = dp[1][i];
	    for (int j = 0; j < i; j++) {
		dp[2][i] = Math.max(dp[2][i],
				    dp[1][j] + prices[i] - min[j][i]);
	    }
	    maxProfit = Math.max(maxProfit, dp[2][i]);
	}	    	
	return maxProfit;
    }
}
