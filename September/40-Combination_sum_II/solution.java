import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Instead of using a set to remove duplicate results, this algorithm avoids
 * duplicates at computing. For each unique candidate number, the algorithm
 * tries all possible repetitions of the number. For example, if the candidates
 * contain three 1s, the algorithm tries zero 1, one 1, two 1s and three 1s.
 * 
 * @author ziqing
 *
 */
class Solution {

	Map<Integer, Map<Integer, List<List<Integer>>>> cache = new HashMap<>();

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);

		int[][] cands = new int[candidates.length][2];
		int prev = candidates[0];
		int candsLen = 0;

		cands[0][0] = prev;
		cands[0][1] = 1;
		for (int i = 1; i < candidates.length; i++) {
			if (candidates[i] == prev)
				cands[candsLen][1]++;
			else {
				cands[++candsLen][0] = candidates[i];
				cands[candsLen][1] = 1;
				prev = candidates[i];
			}
		}
		candsLen++;
		return comb(cands, candsLen, target, 0, 0);
	}

	// pre-cond: currentSum < target
	private List<List<Integer>> comb(int[][] cands, int candsSize, int target,
			int pos, int currentSum) {
		if (candsSize == pos)
			return new LinkedList<>();
		if (target - currentSum < cands[pos][0])
			return new LinkedList<>();

		List<List<Integer>> results = readCache(target - currentSum, pos);

		if (results != null)
			return results;
		results = new LinkedList<>();
		for (int n = 0; n <= cands[pos][1]; n++) {
			int nextSum = cands[pos][0] * n + currentSum;

			if (nextSum == target) {
				List<Integer> suffix = new LinkedList<>();

				// n > 0 at this point
				for (int i = 0; i < n; i++)
					suffix.add(cands[pos][0]);
				results.add(suffix);
				return results;
			} else if (nextSum > target)
				break;
			else {
				for (List<Integer> suffix : comb(cands, candsSize, target,
						pos + 1, nextSum)) {
					for (int i = 0; i < n; i++)
						suffix.add(cands[pos][0]);
					results.add(suffix);
				}
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
		int[] arr = new int[] { 2, 5, 2, 1, 2 };

		for (List<Integer> l : new Solution().combinationSum2(arr, 5))
			System.out.println(l);
	}
}
