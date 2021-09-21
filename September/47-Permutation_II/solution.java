class Solution {

    Set<Integer> sortedIndices = new HashSet<>();

    // pre-cond: 1 <= nums.length <= 8    
    public List<List<Integer>> permuteUnique(int[] nums) {
	return perm(nums);
    }

    private List<List<Integer>> perm(int[] nums) {     
	List<List<Integer>> result = new LinkedList<>();

	if (sortedIndices.size() == nums.length) {
	    result.add(new LinkedList<>());
	    return result;
	}
	
	Set<Integer> numSorted = new TreeSet<>();
	
	for (int i = 0; i < nums.length; i++) {
	    if (numSorted.contains(nums[i]) || sortedIndices.contains(i))
		continue;
	    numSorted.add(nums[i]);
	    sortedIndices.add(i);
	    
	    for (List<Integer> prefix : perm(nums)) {
		prefix.add(nums[i]);
		result.add(prefix);
	    }
	    sortedIndices.remove(i);
	}
	return result;
    }
}
