class Solution {
    public int[] productExceptSelf(int[] nums) {
	int prefix[] = new int[nums.length];
	int suffix[] = new int[nums.length];
	final int len = nums.length;
	
	prefix[0] = 1;
	for (int i = 1; i < nums.length; i++)
	    prefix[i] = nums[i-1] * prefix[i-1];
	suffix[len - 1] = 1;
	for (int i = len-2; i >= 0; i--)
	    suffix[i] = nums[i+1] * suffix[i+1];
	for (int i = 0; i < len; i++)
	    prefix[i] *= suffix[i];	
	return prefix;
    }    
}
