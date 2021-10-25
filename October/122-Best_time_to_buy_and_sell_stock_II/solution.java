class Solution {
    /**
     *  sell at peak buy at valley
     */
    public int maxProfit(int[] prices) {
	int min = prices[0];
	int profit = 0;
	
	for (int i = 1; i < prices.length; i++) {
	    if (prices[i] < prices[i-1]) {
		profit += prices[i-1] - min;
		min = prices[i];
		continue;
	    }
	    if (i == prices.length - 1 && prices[i] > min) {
		profit += prices[i] - min;
		continue;
	    }
	    if (prices[i] < min)
		min = prices[i];	    	    
	}
	return profit;
    }
}
