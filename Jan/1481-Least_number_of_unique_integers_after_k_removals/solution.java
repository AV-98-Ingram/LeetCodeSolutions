class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
	Map<Integer, Integer> map = new HashMap<>();
	Queue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1]));
	
	for (int n : arr)
	    map.merge(n, 1, Integer::sum);
	for (int key : map.keySet())
	    pq.add(new int[] {key, map.get(key)});

	while (!pq.isEmpty() && k > 0) {
	    int[] n = pq.poll();
	    int remove = Math.min(k, n[1]);
	    
	    k -= remove;
	    n[1] -= remove;
	    if (n[1] == 0)
		map.remove(n[0]);
	    // no need to add n back to queue cuz if n[1] has remaining k must reach 0.
	}
	return map.size();
    }
}
