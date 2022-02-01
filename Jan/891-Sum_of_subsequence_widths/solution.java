class Solution {

    /*

      For each subsequnce the only things matter are the min and max
      elements.  So if we fix a pair of min and max, we could freely
      add any element that is in between min and max to the subsequece
      to form a new subsequence.

      Also note that duplicated subsequences are allowed, e.g.,
      For [2, 2, 1, 3], [2,1,3] will be counted twice.

      So the solution would be sorting the elements first. Then for
      each pair a and b, we can count the number of subsequences whose
      min and max are a and b, resp, by doing some calculation on the
      numbers between a and b.

      E.g., [2, 2, 1, 3] is sorted to [1, 2, 2, 3].  To compute the
      subsequences whose min and max are 1 and 3, resp, we compute how
      many subsequences can be formed by numbers between 1 and 3.
      This can be done by DP:

      DP[i]: the number of subseqs of length i.  

      DP[i] = DP[i-1] * 2 + 1, as adding the new i-th element to every
      subseq counted in DP[i-1] forms a new subseq. Plus the i-th
      element itself.
      
     */    
    public int sumSubseqWidths(int[] nums) {
	Arrays.sort(nums);

	final int len = nums.length;
	long[] dp = new long[len];
	long result = 0;

	if (len <= 1)
	    return 0;
	dp[0] = 0;
	dp[1] = 1;

	for (int j = 1; j < len; j++) {
	    int width = nums[j] - nums[0];
	    int nbetween = j - 1;

	    if (nbetween == 0)
		result += width;
	    else if (nbetween == 1)
		result += (width << 1);
	    else {
		dp[nbetween] = (dp[nbetween - 1] * 2 + 1) % 1000000007;
		result += (dp[nbetween] + 1) * width;
	    }
	    result = result % 1000000007;
	}
	for (int i = 1; i < len; i++)
	    for (int j = 1 + i; j < len; j++) {
		int width = nums[j] - nums[i];
		int nbetween = j - i - 1;

		if (nbetween == 0)
		    result += width;
		else if (nbetween == 1)
		    result += (width << 1);
		else
		    result += (dp[nbetween] + 1) * width;
		result = result % 1000000007;
	    }
	return (int) (result % 1000000007);
    }
}
