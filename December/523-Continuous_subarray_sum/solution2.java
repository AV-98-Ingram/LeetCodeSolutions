class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
	final int len = nums.length;
	Map<Integer, Integer> prefixSumMods = new HashMap<>();
	int sum = 0;
	
	for (int i = 0; i < len; i++) {
	    sum += nums[i];

	    int mod = sum % k;	    
	    Integer prev = prefixSumMods.put(mod, i);

	    if (mod == 0 && i >= 1)
		return true;
	    if (prev != null)
		if (i - prev >= 2)
		    return true;
		else
		    prefixSumMods.put(mod, prev);
	}
	return false;
    }
}
