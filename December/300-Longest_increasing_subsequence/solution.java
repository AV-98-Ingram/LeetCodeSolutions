import java.util.SortedSet;
import java.util.TreeSet;

class Solution {
	public int lengthOfLIS(int[] nums) {
		final int len = nums.length;
		int dp[] = new int[len];
		TreeSet<int[]> dpPrefix = new TreeSet<>((a, b) -> {
			int comp = Integer.compare(a[0], b[0]);
			return comp == 0 ? Integer.compare(a[1], b[1]) : comp;
		});
		int max = 0;

		for (int i = 0; i < len; i++) {
			int[] curr = new int[] { nums[i], i };
			SortedSet<int[]> prev = dpPrefix.headSet(new int[] { nums[i], 0 });
			int maxDP = 0;

			for (int[] prevDP : prev)
				maxDP = Math.max(maxDP, dp[prevDP[1]]);
			dp[i] = maxDP + 1;
			dpPrefix.add(curr);
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	public static void main(String[] args) {
		new Solution().lengthOfLIS(new int[] { 3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12 });
	}
}
