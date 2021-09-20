class Solution {   
    
    public List<List<Integer>> permute(int[] nums) {
	return permuteWork(nums, new HashSet<>());
    }

    private List<List<Integer>> permuteWork(int[] nums, Set<Integer> done) {
	List<List<Integer>> result = new LinkedList<>();
	
	for (int n : nums) {
	    if (done.contains(n))
		continue;
	    done.add(n);
	    for (List<Integer> prefix : permuteWork(nums, done)) {
		prefix.add(n);
		result.add(prefix);
	    }
	    done.remove(n);
	}
	return result;
    }
}
