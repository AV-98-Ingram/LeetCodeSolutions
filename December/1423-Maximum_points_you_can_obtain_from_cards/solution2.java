class Solution {

    // Move two pointers: left and right. Initially, left = 0 and
    // right = len-1. Then for each i : (0 .. k), compute the max sum
    // of cards[left-1 .. right-1]. Note that here card[-n] =>
    // card[len-n] for a positive n, n < len-1.

    public int maxScore(int[] cardPoints, int k) {
	final int len = cardPoints.length;
	int left = 0, right = k - 1;
	int sum = 0;
	int max;

	for (int i = left; i <= right; i++)
	    sum += cardPoints[i];
	max = sum;
	for (int i = 1; i <= k; i++) {
	    // shift the window to right by one step:
	    sum -= cardPoints[right]; // at this location, 'right' >= 0 always
	    right--;
	    left--;
	    sum += cardPoints[len + left]; // at this location, 'left' < 0 always
	    max = Math.max(max, sum);
	}
	return max;
    }

    public static void main(String[] args) {
	new Solution().maxScore(new int[] { 1, 2, 3, 4, 5, 6, 1 }, 3);
    }
}

