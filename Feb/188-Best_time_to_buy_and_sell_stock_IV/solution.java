class Solution {
    public int maxProfit(int k, int[] prices) {
	final int len = prices.length;

	if (len == 0 || k == 0)
	    return 0;

	// min[i][j]: min price in prices[i .. j]
	int[][] min = new int[len][len];
	// dp[i][j]: max profit earned after j days with i+1 transactions
	int[][] dp = new int[k][len];

	for (int i = 0; i < len; i++) {
	    min[i][i] = prices[i];
	    for (int j = i + 1; j < len; j++)
		min[i][j] = Math.min(min[i][j - 1], prices[j]);
	}

	int maxProfit = 0;

	dp[0][0] = 0;
	for (int i = 1; i < len; i++) {
	    dp[0][i] = Math.max(dp[0][i - 1], prices[i] - min[0][i]);
	    maxProfit = Math.max(maxProfit, dp[0][i]);
	}
	for (int kk = 1; kk < k; kk++) {
	    for (int i = kk; i < len; i++) {
		dp[kk][i] = dp[kk - 1][i];
		for (int j = 0; j < i; j++)
		    dp[kk][i] = Math.max(dp[kk][i], dp[kk - 1][j] + prices[i] - min[j][i]);
		maxProfit = Math.max(maxProfit, dp[kk][i]);
	    }
	}
	return maxProfit;
    }

    public static void main(String[] args) {
	new Solution().maxProfit(1, new int[] { 1, 2 });
    }
}
