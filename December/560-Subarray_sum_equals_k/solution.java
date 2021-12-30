class Solution {
       
    public int subarraySum(int[] nums, int k) {
	Map<Integer, Integer> prefixSums = new HashMap<>();
	int sum = 0;
	int result = 0;
	
	for (int n : nums) {
	    sum += n;
	    prefixSums.merge(sum, 1, Integer::sum);
	    if (sum == k)
		result++;
	    result += prefixSums.getOrDefault(sum - k, 0);
	    if (k == 0) {
		/* In case of k == 0, we will count the
                 * non-existential sub-array nums[i+1 .. i], where i
                 * is the current reached position. So we need to
                 * delete this sub-array.
		 */
		result--;
	    }
	}
	return result;
    }
}
