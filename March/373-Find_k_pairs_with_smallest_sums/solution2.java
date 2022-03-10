class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
	List<List<Integer>> result = new LinkedList<>();
	Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(nums1[a[0]] + nums2[a[1]], nums1[b[0]] + nums2[b[1]]));
	int lb = nums1.length;
	int rb = nums2.length;
	Set<Integer> inque = new HashSet<>();
	
	for (int r = 0; r < rb; r++) {
	    for (int l = 0; l < lb; l++) {
		pq.add(new int[] {l, r});
		inque.add(l * rb + r);
		if (pq.size() == k)
		    break;
	    }
	    if (pq.size() == k)
		break;	    
	}
	
	while (result.size() < k && !pq.isEmpty()) {
	    int[] pair = pq.poll();
	    
	    result.add(Arrays.asList(nums1[pair[0]], nums2[pair[1]]));
	    
	    if (pair[1] < rb-1) {
		pair[1]++;
		if (!inque.contains(pair[0] * rb + pair[1]))
		    pq.add(pair);
	    }
	}
	return result;
    }
}
