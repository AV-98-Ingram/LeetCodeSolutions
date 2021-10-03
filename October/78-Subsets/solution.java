import java.util.LinkedList;
import java.util.List;

class Solution {

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> results = new LinkedList<>();

		results.add(new LinkedList<>());
		for (int i = 0; i < nums.length; i++)
			results.addAll(f(nums, 0, i + 1));
		return results;
	}

	private List<List<Integer>> f(int[] nums, int start, int k) {
		List<List<Integer>> results = new LinkedList<>();

		if (k == 0) {
			results.add(new LinkedList<>());
			return results;
		}
		for (int i = start; i < nums.length; i++) {
			for (List<Integer> subset : f(nums, i + 1, k - 1)) {
				subset.add(nums[i]);
				results.add(subset);
			}
		}
		return results;
	}

	public static void main(String[] args) {
		for (List<Integer> nums : new Solution().subsets(new int[] { 1, 2, 3 }))
			System.out.println(nums);
	}
}
