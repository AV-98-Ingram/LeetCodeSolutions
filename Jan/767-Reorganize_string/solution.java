class Solution {
    public String reorganizeString(String s) {
	int[] table = new int[26];
	final int size = s.length();
	int max = 0;
	char maxChar = 0;

	for (int i = 0; i < size; i++) {
	    char c = s.charAt(i);

	    table[c - 'a']++;
	    if (table[c - 'a'] > max) {
		max = table[c - 'a'];
		maxChar = c;
	    }
	}
	if (size - max < max - 1)
	    return "";

	StringBuffer[] segments = new StringBuffer[max];
	int nxt = 0;
	boolean completed = false;

	while (!completed)
	    for (int i = 0; i < max; i++) {
		if (segments[i] == null) {
		    segments[i] = new StringBuffer();
		    segments[i].append(maxChar);
		}

		char c = 0;
		int j = 0;

		if (table[nxt] > 0 && nxt != maxChar - 'a') {
		    table[nxt]--;
		    c = (char) ('a' + nxt);
		} else {
		    // update nxt:
		    for (j = nxt + 1; j < 26; j++)
			if (table[j] > 0 && j != maxChar - 'a') {
			    nxt = j;
			    break;
			}
		    table[nxt]--;
		    c = (char) ('a' + nxt);
		}
		if (j == 26)
		    completed = true;
		else
		    segments[i].append(c);
	    }
	for (int i = 1; i < max; i++)
	    segments[0].append(segments[i]);
	return segments[0].toString();
    }

    public static void main(String[] args) {
	new Solution().reorganizeString("bfrbs");
    }
}
