class Solution {

    Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();
    
    public int findTargetSumWays(int[] nums, int target) {
	return f(nums, 0, 0, target);
    }

    private int f(int[] nums, int pos, int sumSoFar, int target) {
	if (pos == nums.length) 
	    return target == sumSoFar ? 1 : 0;

	Integer cached = cache.get(pos).get(sumSoFar);
	int ret = 0;
	
	if (chached != null)
	    return cached;
	ret += f(nums, pos + 1, sumSoFar + nums[pos], target);
	ret += f(nums, pos + 1, sumSoFar - nums[pos], target);

	Map<Integer, Integer> cachedPosResults = cache.computeIfAbsent(pos, new HashMap<>());

	cachedPosResults.put(sumSoFar, ret);
	return ret;
    }
}
