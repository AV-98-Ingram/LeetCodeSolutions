class Solution {
    // O(1) space solution: reverse the string then reverse each word (if String is immutable)    
    public String reverseWords(String s) {
	char[] c = new char[s.length()];
	int pos = 0;
	int i = s.length() - 1;

	while (i >= 0) {
	    while (i >= 0 && s.charAt(i) == ' ')
		i--;
	    while (i >= 0 && s.charAt(i) != ' ')
		c[pos++] = s.charAt(i--);
	    if (i >= 0)
		c[pos++] = ' ';
	}
	while (pos-1 >= 0 && c[pos-1] == ' ')
	    pos--;
	
	// reverse each word then:
	int start = 0;
	while (start < pos) {
	    int end = start;
	    
	    while (end < pos && c[end] != ' ')
		end++;

	    int nxt = end;
	    
	    while (start < end) {
		char tmp = c[start];

		c[start] = c[end-1];
		c[end-1] = tmp;
		start++; end--;
	    }
	    start = nxt+1;
	}
	return new String(Arrays.copyOfRange(c, 0, pos));
    }
}
