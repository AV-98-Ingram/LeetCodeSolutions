class Solution {
    public int minAddToMakeValid(String s) {
        final int len = s.length();
	int opens = 0, closes = 0;

	for (int i = 0; i < len; i++) {
	    char c = s.charAt(i);

	    if (c == ')') {
		if (opens > 0)
		    opens--;
		else
		    closes++;
	    } else 
		opens++;
	}
	return closes + opens;
    }
}
