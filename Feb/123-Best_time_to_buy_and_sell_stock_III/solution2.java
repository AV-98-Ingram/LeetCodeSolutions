class Solution {
    public int maxProfit(int[] prices) {
	final int len = prices.length;
	// dp[i]: the max profit for one transaction since i-th day.
        int dp[] = new int[len];
	int max = prices[len-1];
	
	dp[len-1] = 0;
	for (int i = len-2; i >= 0; i--) {
	    dp[i] = Math.max(dp[i+1], max - prices[i]);
	    max = Math.max(prices[i], max);
	}

	int min = prices[0];
	int maxProfit = dp[0];
	
	for (int i = 1; i < len; i++) {
	    maxProfit = Math.max(maxProfit, prices[i] - min + dp[i]);
	    min = Math.min(min, prices[i]);
	}
	return maxProfit;
    }
}
