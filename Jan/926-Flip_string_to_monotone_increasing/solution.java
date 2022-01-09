class Solution {
    public int minFlipsMonoIncr(String s) {
	final int len = s.length();
	int start = -1;
	int ones = 0;

	for (int i = 0; i < len; i++)
	    if (s.charAt(i) == '1') {
		if (start < 0)
		    start = i;
		ones++;
	    }
	return min(start, len, ones, s);
    }

    private int min(int start, int end, int ones, String s) {
	if (start >= end)
	    return 0;
	if (s.charAt(end - 1) == '0') {
	    // option one: turn all s[start .. end-1] to '0'
	    int opt1 = ones;
	    // option two: turn this to '1' and recursively compute
	    // the next:
	    int opt2 = 1 + min(start, end - 1, ones, s);

	    return Math.min(opt1, opt2);
	} else
	    return min(start, end - 1, ones - 1, s);
    }
}
