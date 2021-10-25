class Solution {
    public int maxProfit(int[] prices) {
	int minSoFar = prices[0];
	int max = 0;
	
	for (int i = 1; i < prices.length; i++) {
	    int profit = prices[i] - minSoFar;
	    
	    if (profit > max)
		max = profit;
	    if (prices[i] < minSoFar)
		minSoFar = prices[i];
	}
	return max;
    }
}
