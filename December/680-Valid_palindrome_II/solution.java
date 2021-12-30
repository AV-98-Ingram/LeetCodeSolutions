class Solution {
    public boolean validPalindrome(String s) {
	return f(s, 0, s.length()-1, true);
    }

    private boolean f(String s, int left, int right, boolean allow2remove) {
	if (left >= right)
	    return true;
	if (s.charAt(left) == s.charAt(right))
	    return f(s, left+1, right-1, allow2remove);
	if (allow2remove)
	    return f(s, left+1, right, false) || f(s, left, right-1, false);
	return false;
    }
}
