class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
	Map<Integer, Integer> prefixSum = new HashMap<>();
	
	int sum = 0;
	int i = 0;

	prefixSum.put(0, 0); // stands for the left bound
	for (int n : nums) {
	    sum += n;
	    i++;
	    prefixSum.putIfAbsent(sum, i);
	}
	sum = 0;
	i = 0;

	int max = 0;
	
	for (int n : nums) {
	    sum += n;
	    i++;
	    
	    int prefixVal = sum - k;
	    int prefixIdx = prefixSum.getOrDefault(prefixVal, i);

	    max = Math.max(max, i - prefixIdx);	    
	}
	return max;
    }
}
