class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
	int l = 0, r = 0, p = 0;
	int n = 0; // number of uniques
	final int len = nums.length;
	int[] table = new int[len + 1];
	int result = 0;

	while (r < len) {
	    if (table[nums[r++]]++ == 0)
		n++;
	    if (n > k) {
		table[nums[p]]--;
		l = p = p + 1;
		n = k;
	    }
	    while (n == k && p < r && table[nums[p]] > 1)
		table[nums[p++]]--;
	    if (n == k)
		result += (p - l) + 1;
	}
	return result;
    }

    public static void main(String[] args) {
	new Solution().subarraysWithKDistinct(new int[] { 1, 2, 3, 1, 2, 4 }, 3);
    }
}

