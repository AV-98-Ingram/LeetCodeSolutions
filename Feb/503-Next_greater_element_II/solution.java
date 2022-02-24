import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
	TreeMap<Integer, List<Integer>> table = new TreeMap<>();
	final int len = nums.length;
	int[] result = new int[len];

	Arrays.fill(result, -1);
	// first scan:
	for (int i = 0; i < len; i++) {
	    table.computeIfAbsent(nums[i], key -> new LinkedList<>()).add(i);

	    List<Integer> removingKeys = new LinkedList<>();

	    for (Map.Entry<Integer, List<Integer>> entry : table.headMap(nums[i]).entrySet()) {
		for (int idx : entry.getValue())
		    result[idx] = nums[i];
		removingKeys.add(entry.getKey());
	    }
	    for (int rmKey : removingKeys)
		table.remove(rmKey);
	}
	// second scan, find the next-greater-one 'm' for each number
	// 'n' remaining in table such that index(m) < index(n). (we
	// know there is no such 'm' that index(m) > index(n))
	for (int i = 0; i < len; i++) {
	    List<Integer> removingKeys = new LinkedList<>();

	    for (Map.Entry<Integer, List<Integer>> entry : table.headMap(nums[i]).entrySet()) {
		List<Integer> remaining = new LinkedList<>();

		for (int idx : entry.getValue())
		    if (idx > i) {
			result[idx] = nums[i];
		    } else
			remaining.add(idx);
		entry.setValue(remaining);
		if (remaining.isEmpty())
		    removingKeys.add(entry.getKey());
	    }
	    for (int rmKey : removingKeys)
		table.remove(rmKey);
	}
	return result;
    }

    public static void main(String[] args) {
	new Solution().nextGreaterElements(new int[] { 5, 4, 3, 2, 1 });
    }
}
