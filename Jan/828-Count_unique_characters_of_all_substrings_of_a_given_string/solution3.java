class Solution {

    /*
      The key idea of this solution is to count for each character
      s[i] in s the number of its' valid appearances in all
      substrings.
      
      The solution only scans the input string once.  During the scan,
      for every letter c out of the 26 letters, we maintain two
      indices:
        c[0]: the last appearance of c in s
	c[1]: the second last appearance of c in s
      They are initially -1.
	
      So for a generic example, suppose character 'X' is at s[i]:

      ... 'X' ... 'X'
           |       |
          c[1]    c[0]  
	  
      We here to count valid appearances of s[i] in substrings ending
      at s[i], i.e., c[0] - c[1], denoted prefix(s[i]).

      In addition, we count valid appearances of s[c[1]] in substrings
      starting from itself, i.e., c[0]- c[1] - 1, denoted
      suffix(s[c[1]]).  Note that we subtract one here because the
      substring "X" shall not be counted twice.

      Finally, we count valid appearances of s[c[1]] in substrings
      that neither start nor end at itself, i.e., 
      (prefix(s[c[1]]) - 1) * suffix(s[c[1]]). 
      Note we subtract prefix(s[c[1]]) by one here to rule out the
      case of substring "X".  Because otherwise, it is a substring starting at s[c[1]].

      After the scan reaches the end, we need to count the valid
      appearances of s[c[0]] for each letter c in s that either starts
      from s[c[0]] or neither start nor end at s[c[0]].    
     */    
    public int uniqueLetterString(String s) {
	// hist[c][0]: the last appearance of letter c
	// hist[c][1]: the second last appearance of letter c
	int hist[][] = new int[26][2];
	final int len = s.length();
	// prefix[i]: the number of counted appearances of s[i] in
	// substrings that ends at s[i].
	int prefix[] = new int[len];
	int result = 0;

	for (int[] h : hist) 
	    h[0] = h[1] = -1;
	for (int i = 0; i < len; i++) {
	    char c = s.charAt(i);

	    prefix[i] = i - hist[c - 'A'][0];
	    result += prefix[i];
	    hist[c - 'A'][1] = hist[c - 'A'][0];
	    hist[c - 'A'][0] = i;
	    if (hist[c - 'A'][1] >= 0) {
		int suffix = hist[c - 'A'][0] - hist[c - 'A'][1] - 1;

		result += suffix + suffix * (prefix[hist[c - 'A'][1]] - 1);
	    }
	}
	for (int h[] : hist) {
	    int suffix = (len - h[0] - 1);

	    result += suffix + (h[0] - h[1] - 1) * suffix;
	}
	return result;
    }

    public static void main(String[] args) {
	new Solution().uniqueLetterString("ABABAB");
    }
}
