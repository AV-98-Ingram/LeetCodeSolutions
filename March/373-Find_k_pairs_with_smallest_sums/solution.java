import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
	List<List<Integer>> result = new LinkedList<>();
	Queue<int[]> pq = new PriorityQueue<>(
					      (a, b) -> Integer.compare(nums1[a[0]] + nums2[a[1]], nums1[b[0]] + nums2[b[1]]));
	int lb = nums1.length;
	int rb = nums2.length;
	Set<Integer> seen = new HashSet<>(lb * rb);
	int max = nums1[0] + nums2[0];
	
	pq.add(new int[] { 0, 0 });
	while (!pq.isEmpty() && result.size() < k) {
	    int[] pair = pq.poll();

	    result.add(Arrays.asList(nums1[pair[0]], nums2[pair[1]]));
	    if (pair[0] < lb - 1) {
		if (seen.add((pair[0] + 1) * rb + pair[1])) {
		    int sum = nums1[pair[0] + 1] + nums2[pair[1]];

		    if (sum < max || result.size() + pq.size() < k) {		
			pq.add(new int[] { pair[0] + 1, pair[1] });
			max = Math.max(sum, max);
		    }
		}
	    }
	    if (pair[1] < rb - 1) {
		if (seen.add((pair[0]) * rb + pair[1] + 1)) {
		    int sum = nums1[pair[0]] + nums2[pair[1] + 1];

		    if (sum < max || result.size() + pq.size() < k) {
			pq.add(new int[] { pair[0], pair[1] + 1 });
			max = Math.max(sum, max);
		    }
		}
	    }
	}
	return result;
    }
}
