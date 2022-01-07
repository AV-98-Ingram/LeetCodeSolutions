class Solution {
    // O(n * m), too slow
    public int uniqueLetterString(String s) {
	final int len = s.length();
	int total = 0;

	for (int i = 0; i < len; i++) {
	    int sum = 1;
	    int table[] = new int[26];

	    total++;
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
		total += sum;
	    }
	}
	return total;
    }

    public static void main(String[] args) {
	new Solution().uniqueLetterString("LEEE");
    }
}
