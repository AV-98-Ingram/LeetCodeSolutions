class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
	final int len = nums.length;

	for (int i = 0; i < len; i++) {
	    int sum = nums[i];
	    
	    for (int j = i+1; j < len; j++) {
		sum += nums[j];		
		if (sum % k == 0)
		    return true;
	    }
	}
	return false;
    }
}
