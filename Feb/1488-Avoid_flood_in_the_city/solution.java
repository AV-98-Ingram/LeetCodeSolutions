 class Solution {
    
    public int[] avoidFlood(int[] rains) {
	final int len = rains.length;
	int ans[] = new int[len];
	Map<Integer, Integer> lakes = new HashMap<>();
	TreeSet<Integer> dries = new TreeSet<>();
	
	Arrays.fill(ans, -1);
	for (int i = 0; i < len; i++) {
	    int r = rains[i];
	    
	    if (r > 0) {
		Integer last = lakes.put(r, i);

		if (last == null)
		    continue;
		
		// get the first dry day between last and i
		Integer dry = dries.higher(last);

		if (dry == null || dry > i)
		    return new int[0];
		ans[dry] = r;
		dries.remove(dry);
	    } else 
		dries.add(i);
	}
	for (int d : dries)
	    ans[d] = 1;
	return ans;
    }
}
