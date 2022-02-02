class Solution {
    public int[] productExceptSelf(int[] nums) {
	int prefix[] = new int[nums.length];
	final int len = nums.length;
	int suffixProd = 1;	
	
	prefix[0] = 1;
	for (int i = 1; i < len; i++)
	    prefix[i] = nums[i-1] * prefix[i-1];
	for (int i = len-1; i >= 0; i--) {
	    prefix[i] *= suffixProd;
	    suffixProd *= nums[i];
	}	
	return prefix;
    }    
}
