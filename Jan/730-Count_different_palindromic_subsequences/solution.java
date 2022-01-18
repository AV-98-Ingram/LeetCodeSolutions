class Solution {
    public int countPalindromicSubsequences(String s) {
	final int len = s.length();
	long dp[][] = new long[len][len + 1];

	return (int) f(s, 0, len, dp);
    }

    private long f(String s, int start, int end, long[][] cache) {
	if (start >= end)
	    return 0;
	if (start + 1 == end)
	    return 1;
	if (cache[start][end] > 0)
	    return cache[start][end];

	char left = s.charAt(start);
	char right = s.charAt(end - 1);

	if (left != right) {
	    long prefix = f(s, start, end - 1, cache);
	    long suffix = f(s, start + 1, end, cache);
	    long dups = f(s, start + 1, end - 1, cache);

	    cache[start][end] = (prefix + suffix + 1000000007 - dups) % 1000000007;
	    return cache[start][end];
	} else {
	    int innerLeft = -1, innerRight = -1;

	    for (int i = start + 1; i < end - 1; i++)
		if (s.charAt(i) == left) {
		    if (innerLeft < 0)
			innerLeft = i;
		    innerRight = i;
		}

	    long innerResult = f(s, start + 1, end - 1, cache);

	    if (innerRight < 0) {
		// no s[start] in s[start + 1 .. end-1]:
		innerResult = (innerResult + innerResult) + 2;
	    } else if (innerLeft == innerRight) {
		// there is exact one s[start] in s[start + 1 .. end-1]:
		innerResult = (innerResult + innerResult) + 1;
	    } else {
		// we have the outermost inner pair of s[start] in
		// s[start + 1 .. end-1]:
		innerResult = innerResult + innerResult + 1000000007 - f(s, innerLeft + 1, innerRight, cache);
	    }
	    cache[start][end] = innerResult % 1000000007;
	    return cache[start][end];
	}
    }

    public static void main(String[] args) {
	new Solution().countPalindromicSubsequences("bcc");
    }
}
