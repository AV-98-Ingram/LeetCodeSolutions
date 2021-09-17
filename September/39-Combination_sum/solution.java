import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Simple dynamic programming. Be careful that when caching lists, making copies
 * for the caching lists otherwise the lists in the cache could be modified by
 * the algorithm.
 *
 * Also tried parallelize this algorithm, not really speedup probably
 * due to small candidate sizes.
 * 
 * @author ziqing
 *
 */
class Solution {

	Map<Integer, Map<Integer, List<List<Integer>>>> cache = new HashMap<>();

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		Arrays.sort(candidates);

		List<List<Integer>> results = new LinkedList<>();

		// pre-cond: target > 0
		return comb(candidates, target, 0, 0, results);
	}

    //requires: currentSum < target
    private List<List<Integer>> comb(int[] candidates, int target, int pos, int currentSum) {
	List<List<Integer>> results = readCache(target - currentSum, pos);
	
	if (results != null) 
	    return results;
	results = new LinkedList<>();
	for (int i = pos; i < candidates.length; i++) {
	    int nextSum = currentSum + candidates[i];

	    if (nextSum == target) {
		List<Integer> suffix = new LinkedList<>();
		
		suffix.add(candidates[i]);
		results.add(suffix);
		return results;
	    } else if (nextSum > target)
		break;
	    	    
	    for (List<Integer> suffix : comb(candidates, target, i, nextSum)) {
		suffix.add(candidates[i]);
		results.add(suffix);
	    }
	}
	writeCache(target - currentSum, pos, results);
	return results;
    }
    
    private List<List<Integer>> readCache(int sum, int pos) {
	Map<Integer, List<List<Integer>>> subMap = cache.get(pos);
	
		if (subMap != null) {
			List<List<Integer>> valCopy = new LinkedList<>();
			List<List<Integer>> val = subMap.get(sum);

			if (val != null) {
				for (List<Integer> v : val)
					valCopy.add(new LinkedList<>(v));
				return valCopy;
			}
		}
		return null;
	}

	private void writeCache(int sum, int pos, List<List<Integer>> val) {
		Map<Integer, List<List<Integer>>> subMap = cache.computeIfAbsent(pos,
				(k) -> (new HashMap<>()));
		List<List<Integer>> valCopy = new LinkedList<>();

		for (List<Integer> v : val)
			valCopy.add(new LinkedList<>(v));
		subMap.put(sum, valCopy);
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 2, 7, 6, 3, 5, 1 };

		for (List<Integer> l : new Solution().combinationSum(arr, 9))
			System.out.println(l);
	}
}
