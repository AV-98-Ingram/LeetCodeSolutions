class Solution {
    public String getHint(String secret, String guess) {
        char[] s = secret.toCharArray();
	char[] g = guess.toCharArray();
	final int len = s.length;
	Set<Integer> bullIndices = new HashSet<>(len);
	Map<Integer, Integer> cowNumbers = new HashMap<>();

	for (int i = 0; i < len; i++)
	    if (s[i] == g[i])
		bullIndices.add(i);
	    else 
		cowNumbers.merge(s[i] - '0', 1, Integer::sum);

	int cows = 0;
	
	for (int i = 0; i < len; i++)
	    if (!bullIndices.contains(i)) {
		Integer newVal = cowNumbers.computeIfPresent(g[i] - '0', (k, v)->v-1);

		if (newVal != null && newVal >= 0)
		    cows++;
	    }
	return bullIndices.size() + "A" + cows + "B";
    }
}
