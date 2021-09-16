import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

/* I'm suprised that LeetCode supports multi-threaded Java
 */

class Solution {

	public List<List<Integer>> threeSum(int[] nums) {
		ArrayList<Integer[]> group1 = new ArrayList<>(),
				group2 = new ArrayList<>();
		Set<Integer> group1Set = new HashSet<>(), group2Set = new HashSet<>();
		List<List<Integer>> result = new LinkedList<>();
		ConcurrentMap<ArrayList<Integer>, Integer> tmpResult = new ConcurrentHashMap<>();

		if (nums.length < 3)
			return result;

		if (partition(nums, group1, group1Set, group2, group2Set)) {
			List<Integer> threeZeroes = new LinkedList<>(
					Arrays.asList(0, 0, 0));

			result.add(threeZeroes);
		}

		group1.parallelStream()
				.forEach(new MyConsumer(tmpResult, group1, false, group2Set));
		group2.parallelStream()
				.forEach(new MyConsumer(tmpResult, group2, true, group1Set));

		for (List<Integer> list : tmpResult.keySet())
			result.add(list);
		return result;
	}

	private boolean partition(int[] nums, ArrayList<Integer[]> group1,
			Set<Integer> group1Set, ArrayList<Integer[]> group2,
			Set<Integer> group2Set) {
		int numZeroes = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] >= 0) {
				int group1Size = group1.size();

				group1.add(new Integer[] { nums[i], group1Size });
				group1Set.add(nums[i]);
				if (nums[i] == 0)
					numZeroes++;
			} else {
				int group2Size = group2.size();

				group2.add(new Integer[] { -nums[i], group2Size });
				group2Set.add(-nums[i]);
			}
		}
		return numZeroes >= 3;
	}

	private class MyConsumer implements Consumer<Integer[]> {

		private final ConcurrentMap<ArrayList<Integer>, Integer> map;

		private final ArrayList<Integer[]> group;

		private boolean isNegativeGroup;

		private final Set<Integer> testGroup;

		MyConsumer(ConcurrentMap<ArrayList<Integer>, Integer> map, ArrayList<Integer[]> group, boolean isNegativeGroup, Set<Integer> testGroup) {
			this.map = map;
			this.group = group;
			this.isNegativeGroup = isNegativeGroup;
			this.testGroup = testGroup;
		}

		@Override
		public void accept(Integer[] t) {
			int groupSize = group.size();

			for (int j = t[1] + 1; j < groupSize; j++) {
				if (testGroup.contains(t[0] + group.get(j)[0])) {
					Integer[] triplet = new Integer[3];

					if (isNegativeGroup) {
						triplet[0] = -t[0];
						triplet[1] = -group.get(j)[0];
						triplet[2] = t[0] + group.get(j)[0];
					} else {
						triplet[0] = t[0];
						triplet[1] = group.get(j)[0];
						triplet[2] = -(t[0] + group.get(j)[0]);
					}
					Arrays.sort(triplet);
					map.put(new ArrayList<>(Arrays.asList(triplet)), 0);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] input = new int[] { -1, 0, 1, 2, -1, -4 };

		System.out.println(new Solution().threeSum(input));
	}
}
