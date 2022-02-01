import java.util.Arrays;

class Solution {

    public int sumSubseqWidths(int[] nums) {
	Arrays.sort(nums);

	final int len = nums.length;
	long[] dp = new long[len];
	long pos = 0;
	long neg = 0;

	if (len <= 1)
	    return 0;
	dp[0] = 0;
	dp[1] = 1;
	for (int i = 2; i < len; i++)
	    dp[i] = (dp[i - 1] * 2 + 1) % 1000000007;
	for (int i = 0; i < len; i++) {
	    pos += nums[i] * dp[i];
	    neg += nums[i] * dp[len - 1 - i];
	    neg = neg % 1000000007;
	    pos = pos % 1000000007;
	}
	return (int) ((pos + 1000000007 - neg) % 1000000007);
    }
}
