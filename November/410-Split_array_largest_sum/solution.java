class Solution {
	/**
	 * Classic hard-ass DP problems.
	 * 
	 * Let <code>a</code> be the input array. Then, let's define <code>
	 * int dp[m][n], where
	 * 
	 * dp[0][0]: the min sum over (at most) 1 partition of array a[0 .. 0], which
	 * is 0.
	 * 
	 * dp[i][j]: the min sum over (at most) i+1 partition of array a[0 .. j]
	 * </code>
	 * 
	 * To compute dp[i][j+1]: <code>
	 *  
	 *  // we are standing at the place where min sum over {m, m-1, ..., 1} partitions of a[0 .. j] has been computed:
	 *  a[0 .. j], a[j+1];  
	 *  
	 *  // then our choices are:
	 *  // get the best m-1 partition of a[0 .. j] and make a[j+1] the m-th partition; or
	 *  // get the best m-1 partition of a[0 .. j-1] and make a[j .. j+1] the m-th partition; or
	 *  // get the best m-1 partition of a[0 .. j-2] and make a[j-1 .. j+1] the m-th partition; or
	 *  // ...
	 *  // until the min sum of the best m-1 partition of a[0 .. k] is less than sum(a[k+1 .. j+1]). 
	 *  // No need to continue after this point as the min sum of all the m partitions can only monotonically grow afterwards.
	 *  // What we need is to select the minimum solution among the choices above.
	 *  // Therefore, the pseudo-code is like:
	 *  
	 *  int k = j-1;
	 *  int min = Max(dp[i-1][j], a[j+1]);
	 *  int sum = a[j] + a[j+1];
	 *  while (k >= 0 && dp[i-1][k] >= sum) {
	 *    // update min
	 *    // update sum
	 *    k--;
	 *  }
	 *  dp[i][j+1] = Min(min, sum);
	 * </code>
	 */
	public int splitArray(int[] nums, int m) {
		int partMin[][] = new int[m][nums.length];

		// init partMin[0][0 .. n-1]:
		partMin[0][0] = nums[0];
		for (int i = 1; i < nums.length; i++)
			partMin[0][i] = partMin[0][i - 1] + nums[i];
		// continue to compute partMin[1 .. m-1][0 .. n-1]:
		for (int i = 1; i < m; i++) {
			// compute partMin[i][0 .. n-1]:
			partMin[i][0] = nums[0];
			for (int j = 1; j < nums.length; j++) {
				int min = Math.max(partMin[i - 1][j - 1], nums[j]);
				int k = j - 2;
				int sum = nums[j] + nums[j - 1];

				while (k >= 0 && partMin[i - 1][k] >= sum) {
					min = Math.min(min, partMin[i - 1][k]);
					sum += nums[k];
					k--;
				}
				min = Math.min(min, sum);
				partMin[i][j] = min;
			}
		}
		return partMin[m - 1][nums.length - 1];
	}

	public static void main(String[] args) {
		System.out.println(new Solution().splitArray(new int[] { 1, 4, 4 }, 3));
	}
}
