import java.util.BitSet;

class Solution {

    Boolean cache[][][][];

    public boolean isScramble(String s1, String s2) {
	char[] sarr1 = s1.toCharArray();
	char[] sarr2 = s2.toCharArray();

	if (sarr1.length != sarr2.length)
	    return false;
	cache = new Boolean[sarr1.length + 1][sarr1.length + 1][sarr1.length
								+ 1][sarr1.length + 1];
	return f(sarr1, 0, sarr1.length, sarr2, 0, sarr2.length);
    }

    private boolean f(char[] s1, int begin1, int end1, char[] s2, int begin2,
		      int end2) {
	if (begin1 == end1 - 1)
	    return s1[begin1] == s2[begin2];

	if (cache[begin1][end1][begin2][end2] != null)
	    return cache[begin1][end1][begin2][end2];

	BitSet charS1 = new BitSet(26);
	BitSet charNS2 = new BitSet(26);
	BitSet charS2 = new BitSet(26);

	charS1.clear();
	charNS2.clear();
	charS2.clear();
	// if only s1[0 .. len) matches s2[0 .. len), it's a fail:
	for (int d = begin1; d < end1 - 1; d++) {
	    incr(s1[d], charS1);
	    // for not swap:
	    incr(s2[begin2 + (d - begin1)], charNS2);
	    // for swapped
	    incr(s2[end2 - 1 - (d - begin1)], charS2);
	    if (charS1.equals(charNS2))
		if (f(s1, begin1, d + 1, s2, begin2, begin2 + (d - begin1) + 1))
		    if (f(s1, d + 1, end1, s2, begin2 + (d - begin1) + 1,
			  end2)) {
			cache[begin1][end1][begin2][end2] = true;
			return true;
		    }
	    if (charS1.equals(charS2))
		if (f(s1, begin1, d + 1, s2, end2 - (d - begin1) - 1, end2))
		    if (f(s1, d + 1, end1, s2, begin2,
			  begin2 + (end1 - d - 1))) {
			cache[begin1][end1][begin2][end2] = true;
			return true;
		    }
	}
	cache[begin1][end1][begin2][end2] = false;
	return false;
    }

    private void incr(char c, BitSet bs) {
	int ci = c - 'a';
	while (bs.get(ci)) {
	    ci += 26;
	}
	bs.set(ci);
    }

    public static void main(String[] args) {
	System.out.println(new Solution().isScramble(
						     "eebaacbcbcadaaedceaaacadccd", "eadcaacabaddaceacbceaabeccd"));
    }
}
