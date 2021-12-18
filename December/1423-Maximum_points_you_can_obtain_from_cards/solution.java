class Solution {
    /* brutal force, too slow */
    public int maxScore(int[] cardPoints, int k) {
        return take(cardPoints, 0, cardPoints.length-1, k, 0);
    }


    private int take(int[] cardPoints, int l, int r, int k, int sum) {
	if (k == 0)
	    return sum;

	int sum1 = take(cardPoints, l+1, r, k-1, sum + cardPoints[l]);
	int sum2 = take(cardPoints, l, r-1, k-1, sum + cardPoints[r]);

	return Math.max(sum1, sum2);
    }
}
