class Solution {
    // optimized O(n*m), still slow though
    public int uniqueLetterString(String s) {
	final int len = s.length();
	int total = 0;
	int nxt_i = 0;

	for (int i = 0; i < len;) {
	    int subTotal = 0;
	    int sum = 1;
	    int table[] = new int[26];

	    subTotal++;
	    nxt_i = i;
	    table[s.charAt(i) - 'A']++;
	    for (int j = i + 1; j < len; j++) {
		char c = s.charAt(j);

		if (table[c - 'A'] == 1) {
		    sum--;
		    table[c - 'A']++;
		} else if (table[c - 'A'] == 0) {
		    sum++;
		    table[c - 'A']++;
		}
		subTotal += sum;
		if (j == nxt_i + 1 && c == s.charAt(nxt_i))
		    nxt_i = j;
	    }
	    if (nxt_i == i)
		i = nxt_i + 1;
	    else {
		total += (nxt_i - i - 1) * subTotal;
		i = nxt_i;
	    }
	    total += subTotal;
	}
	return total;
    }

    public static void main(String[] args) {
	new Solution().uniqueLetterString("AAAB");
    }
}
