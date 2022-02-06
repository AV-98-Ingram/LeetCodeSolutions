class Solution {
    public int maxProfit(int k, int[] prices) {
	final int len = prices.length;

	if (len == 0 || k == 0)
	    return 0;

	int dp[][][] = new int[k + 1][len][2];
	int profit = 0;

	dp[0][0][0] = 0;
	dp[0][0][1] = -prices[0];
	for (int i = 1; i < len; i++) {
	    dp[0][i][0] = 0;
	    dp[0][i][1] = Math.max(-prices[i], dp[0][i - 1][1]);
	}

	for (int kk = 1; kk <= k; kk++) {
	    // It won't find any new in exploring kk transactions in
	    // kk-1 days so the inner loop starts from i == kk. But we
	    // still to given dp[kk][kk-1][0..1] a reasonable initial
	    // value:
	    dp[kk][kk - 1][0] = dp[kk - 1][kk - 1][0]; // allowed to do at most kk transactions but only conducts kk-1 transactions
	    dp[kk][kk - 1][1] = dp[kk - 1][kk - 1][1]; 
	    // stock in day[0 .. kk-1]
	    for (int i = kk; i < len; i++) {
		dp[kk][i][0] = Math.max(dp[kk][i - 1][0], dp[kk - 1][i - 1][1] + prices[i]);
		dp[kk][i][1] = Math.max(dp[kk][i - 1][1], dp[kk][i - 1][0] - prices[i]);
		profit = Math.max(dp[kk][i][0], profit);
	    }
	}
	return profit;
    }

    public static void main(String[] args) {
	new Solution().maxProfit(5, new int[] { 3, 2, 5, 0, 3, 1, 4, 1, 6 });
    }
}
