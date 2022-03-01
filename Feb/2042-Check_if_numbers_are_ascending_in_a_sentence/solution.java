class Solution {
    public boolean areNumbersAscending(String s) {
	String[] words = s.split(" ");	
	int prev = -1;
	
	for (String w : words) {
	    int n = parse(w);
	    
	    if (n > 0)
		if (n > prev)
		    prev = n;
		else
		    return false;		
	}
	return true;
    }

    private int parse(String word) {
	int n = 0;
	
	for (char c  : word.toCharArray()) {
	    if ('0' <= c && c <= '9') {
		int dig = c - '0';

		n = 10 * n + dig;
	    } else
		return -1;
	}
	return n;
    }
}
