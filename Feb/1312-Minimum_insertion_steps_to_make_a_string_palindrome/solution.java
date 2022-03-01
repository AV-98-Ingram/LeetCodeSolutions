class Solution {

    Integer cache[][];
    
    public int minInsertions(String s) {
	char[] ss = s.toCharArray();
	int l = 0, r = s.length()-1;

	cache = new Integer[r+1][r+1];
	return topdown(ss, l, r);
    }

    private int topdown(char[] s, int l, int r) {
	if (l >= r)
	    return 0;
	if (cache[l][r] != null)
	    return cache[l][r];

	int min = topdown(s, l+1, r-1);

	min = s[l] == s[r] ? min : min + 2;

	int inserts = topdown(s, l+1, r) + 1;

	min = Math.min(min, inserts);
	inserts = topdown(s, l, r-1) + 1;
	min = Math.min(min, inserts);
	cache[l][r] = min;
	return min;
    }
}
