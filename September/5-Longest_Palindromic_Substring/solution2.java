/*
  Stupid LeetCode cannot compile my solution2.c correctly. Fuck
  it! Translate solution2.c to java.
*/

class Solution {
    
    int max = 0, ret_l = 0, ret_r = 0;
    
    private int min(int x, int y) {return x > y ? y : x;}
    
    private void palind(String s, int left, int right, int len) {
	boolean isEven = (right - left) == 1;
	int possible_max;
	int size = 0;
	
	if (isEven)
	    possible_max = min(left + 1, len - right) * 2;
	else
	    possible_max = min(left, len - right - 1) * 2 + 1;
	if (possible_max <= max)
	    return;
	
	while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
	    size += 2; left -= 1; right += 1;
	}
	size -= isEven ? 0 : 1;
	if (size > max) {
	    max = size;
	    ret_l = left + 1;
	    ret_r = right - 1;
	}
    }
    
    public String longestPalindrome(String s) {
	int len = s.length();
	int left = len / 2;
	int right = left + 1;
	
	while (left >= 0) {
	    palind(s, left, left, len);
	    if (left + 1 < len)
		palind(s, left, left + 1, len);
	    left--;
	}
	while (right < len) {
	    palind(s, right, right, len);
	    palind(s, right - 1, right, len);
	    right++;
	}
	return s.subSequence(ret_l, ret_r+1).toString();   
    }
}

