import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

class Solution {
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i : nums)
			map.merge(i, 1, Integer::sum);

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));

		for (Entry<Integer, Integer> pair : map.entrySet())
			pq.add(new int[] { pair.getKey(), pair.getValue() });

		int[] result = new int[k];

		for (int i = 0; i < k; i++) {
			result[i] = pq.poll()[0];
		}
		return result;
	}

	public static void main(String[] args) {
		new Solution().topKFrequent(new int[] { 5, 3, 1, 1, 1, 3, 73, 1 }, 1);
	}
}
