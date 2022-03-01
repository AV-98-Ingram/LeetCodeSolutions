class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
	List<List<Integer>> results = new LinkedList<>();
	final int len = words.length;
	
	for (int i = 0; i < len; i++)
	    for (int j = i+1; j < len; j++) {
		if (check(words[i], words[j])) {
		    results.add(Arrays.asList(i, j));
		    if (words[i].length() == words[j].length() ||		
			words[i].length() == 0 || words[j].length() == 0)
			results.add(Arrays.asList(j, i)); // optimized case
		    else if (check(words[j], words[i]))
			results.add(Arrays.asList(j, i));	 
		} else if (check(words[j], words[i]))
		    results.add(Arrays.asList(j, i));
	    }
	return results;
    }

    private boolean check(String a, String b) {
	final int aLen = a.length();
	final int bLen = b.length();
	int l = 0, r = bLen - 1;
	
	while (l < aLen && r >= 0) {
	    if (a.charAt(l) != b.charAt(r))
		return false;
	    l++; r--;
	}
	if (l < aLen) {
	    r = aLen - 1;
	    b = a;
	} else if (r >= 0) {
	    l = 0;
	    a = b;
	}
	while (l < r)
	    if (a.charAt(l++) != b.charAt(r--))
		return false;
	return true;
    }
}
