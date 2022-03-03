class Solution {
    public int rob(int[] nums) {
	final int len = nums.length;
        int[] dp = new int[len]; // max money after rob through house[0 .. i] and house[i] was robbed.
	int max = 0;

	if (len < 2)
	    return nums[0];
	
	dp[0] = nums[0];
	dp[1] = Math.max(nums[1], nums[0]);
	max = dp[1];
	for (int i = 2; i < len; i++) {
	    dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
	    max = Math.max(max, dp[i]);
	}
	return max;
    }
}
