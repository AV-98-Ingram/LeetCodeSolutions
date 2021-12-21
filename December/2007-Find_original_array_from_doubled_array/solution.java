class Solution {
    public int[] findOriginalArray(int[] changed) {
	Map<Integer, Integer> unmatched = new HashMap<>();
	int[] result = new int[changed.length / 2];
	int i = 0;
	
	Arrays.sort(changed);
	for (int n : changed) {
	    if ((n & 1) == 0) {
		int m = n / 2;
		Integer ct = unmatched.computeIfPresent(m, (k,v)->v-1);
		
		if (ct != null) {
		    result[i++] = m;
		    if (ct == 0)
			unmatched.remove(m);
		    continue;		
		}
	    }
	    unmatched.merge(n, 1, (v1,v2)->v1 + v2);
	}
	if (unmatched.isEmpty())
	    return result;
	return new int[0];
    }
}
