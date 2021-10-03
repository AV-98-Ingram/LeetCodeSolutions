import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Similar idea of getting all k-sized unordered-combinations: <code>
 * 
 *        1          2            3            4     ... n
 *      /   \       / \          / \          / \  
 *     2 ..  n     3 ..n        4 ..n         ....
 * </code> The difference is that the given array contains duplications. The
 * existence of duplicates require two extra operations: 1) sorting the array
 * and 2) skip seen numbers at each level. Sorting is required otherwise it may
 * produce duplications. For example, given {1, 2, 1}, if not sorting, we will
 * have: <code>
 *       1    2    1
 *      / \   |
 *      2 1   1
 * </code>, where {1,2} and {2,1} repeat each other. Skipping seen numbers at
 * each level works like this: <code>
 *   1   1   2 (after sorting)
 *   
 *   1   1 (skip)  2    (after duplicates removal at level 1)
 *  / \           
 *  1  2
 * </code> The idea behind of the skipping of duplicates at each level is that
 * at each level a number only appears once.
 * 
 * @author ziqing
 *
 */
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
	List<List<Integer>> results = new LinkedList<>();

	Arrays.sort(nums);
	for (int i = 0; i <= nums.length; i++)
	    results.addAll(f(nums, 0, i));
	return results;
    }

    private List<List<Integer>> f(int[] nums, int start, int k) {
	List<List<Integer>> results = new LinkedList<>();

	if (k == 0) {
	    results.add(new LinkedList<>());
	    return results;
	}

	Set<Integer> set = new HashSet<>();

	for (int i = start; i < nums.length; i++) {
	    if (set.contains(nums[i]))
		continue;
	    set.add(nums[i]);
	    for (List<Integer> subset : f(nums, i + 1, k - 1)) {
		subset.add(nums[i]);
		results.add(subset);
	    }
	}
	return results;
    }

    static public void main(String[] args) {
	for (List<Integer> l : new Solution()
		 .subsetsWithDup(new int[] { 4, 4, 4, 1, 4 }))
	    System.out.println(l);
    }
}
