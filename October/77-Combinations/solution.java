import java.util.LinkedList;
import java.util.List;

/**
 * The idea is to add a bound k on the recursion depths for finding all
 * unordered-combinations of (1 .. n).
 */
class Solution {

	public List<List<Integer>> combine(int n, int k) {
		return combineWork(0, n, k);
	}

	// return all unordered-combinations of (start .. n) with a bound k
	private List<List<Integer>> combineWork(int start, int n, int k) {
		List<List<Integer>> results = new LinkedList<>();

		if (k == 0) {
			List<Integer> empty = new LinkedList<>();

			results.add(empty);
			return results;
		}
		for (int i = start; i < n; i++) {
			for (List<Integer> prefix : combineWork(i + 1, n, k - 1)) { 
				prefix.add(i + 1);
				results.add(prefix);
			}
		}
		return results;
	}

	public static void main(String[] args) {
		for (List<Integer> nums : new Solution().combine(4, 2))
			System.out.println(nums);
	}
}
