class Solution {
    public int connectSticks(int[] sticks) {
        Queue<Integer> pq = new PriorityQueue<>();

	for (int s : sticks)
	    pq.add(s);

	int cost = 0;
	
	while (pq.size() > 1) {
	    int s1 = pq.poll();
	    int s2 = pq.poll();
	    int s = s1 + s2;
	    
	    cost += s;
	    pq.add(s);
	}
	return cost;
    }
}
