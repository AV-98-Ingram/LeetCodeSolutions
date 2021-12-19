class Solution {
    public int minPairSum(int[] nums) {
	Arrays.sort(nums);

	int l = 0, r = nums.length-1;
	int max = 0;
	
	while (l < r) {
	    max = Math.max(nums[l] + nums[r], max);
	    l++; r--;
	}
	return max;
    }
}
