import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*  Suppose we divide the input into two groups. 
      Group1: non-negatives, and
      Group2: negatives. 
      Let n be the size of the Group1 and m be the size of Group2.
    
    The algorithm could be 
    for (i : 0 .. n)
      for (j : i+1 .. n)
        if (Group2.contains(nums[i] + nums[j]))
          find a triplet
    Ditto for group 2.
    
    Theorem: The algorithm covers all possible cases except (0, 0, 0).

    Justification: Every triplet consists of at most two numbers of
    the same sign.  The two numbers of the same sign must come from
    Group1 or Group2.

    Note to myself: Be careful with the case (0, 0, 0).  It is included in the result if there are more than three 0s in the input numbers.
 */

class Solution {

	public List<List<Integer>> threeSum(int[] nums) {
		ArrayList<Integer> group1 = new ArrayList<>(),
				group2 = new ArrayList<>();
		Set<Integer> group1Set = new HashSet<>(), group2Set = new HashSet<>();
		List<List<Integer>> result = new LinkedList<>();
		Set<ArrayList<Integer>> tmpResult = new HashSet<>();

		if (nums.length < 3)
			return result;

		if (partition(nums, group1, group1Set, group2, group2Set)) {
			List<Integer> threeZeroes = new LinkedList<>(
					Arrays.asList(0, 0, 0));

			result.add(threeZeroes);
		}

		int group1Size = group1.size();

		for (int i = 0; i < group1Size; i++) {
			for (int j = i + 1; j < group1Size; j++) {
				if (group2Set.contains(group1.get(i) + group1.get(j))) {
					Integer[] triplet = new Integer[3];

					triplet[0] = -(group1.get(i) + group1.get(j));
					triplet[1] = group1.get(j);
					triplet[2] = group1.get(i);
					Arrays.sort(triplet, 1, 3);
					tmpResult.add(new ArrayList<>(Arrays.asList(triplet)));
				}
			}
		}

		int group2Size = group2.size();

		for (int i = 0; i < group2Size; i++) {
			for (int j = i + 1; j < group2Size; j++) {
				if (group1Set.contains(group2.get(i) + group2.get(j))) {
					Integer[] triplet = new Integer[3];

					triplet[0] = -group2.get(i);
					triplet[1] = -group2.get(j);
					triplet[2] = group2.get(i) + group2.get(j);
					Arrays.sort(triplet, 0, 2);
					tmpResult.add(new ArrayList<>(Arrays.asList(triplet)));
				}
			}
		}
		for (List<Integer> list : tmpResult)
			result.add(list);
		return result;
	}

	private boolean partition(int[] nums, ArrayList<Integer> group1,
			Set<Integer> group1Set, ArrayList<Integer> group2,
			Set<Integer> group2Set) {
		int numZeroes = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] >= 0) {
				group1.add(nums[i]);
				group1Set.add(nums[i]);
				if (nums[i] == 0)
					numZeroes++;
			} else {
				group2.add(-nums[i]);
				group2Set.add(-nums[i]);
			}
		}
		return numZeroes >= 3;
	}
}
