class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
	int dp[] = new int[nums.length];
	int leftBound = -1;
	int result = 0;

	
	dp[0] = nums[0] >= left && nums[0] <= right ? 1 : 0;
	leftBound = (nums[0] > right) ? 0 : -1;
	result += dp[0];
	for (int i = 1; i < nums.length; i++) {
	    if (nums[i] < left)
		dp[i] = dp[i-1];
	    else if (nums[i] > right) {
		dp[i] = 0;
		leftBound = i;
	    } else
		dp[i] = i - leftBound;
	    result += dp[i];
	}
	return result;
    }

    public static void main(String[] args) {
	new Solution().numSubarrayBoundedMax(new int[] { 73, 55, 36, 5, 55, 14, 9, 7, 72, 52 }, 32, 69);
    }
}
