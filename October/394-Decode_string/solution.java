class Solution {

    private class Pair {
	String s;
	int next;

	Pair(String s, int next) {
	    this.s = s;
	    this.next = next;
	}
    }

    public String decodeString(String s) {
	return parse(s.toCharArray(), 0).s;
    }

    private Pair parse(char[] s, int pos) {
	StringBuffer sb = new StringBuffer();

	while (pos < s.length && s[pos] != ']') {
	    int repeats = 0;

	    while ('0' <= s[pos] && s[pos] <= '9')
		repeats = repeats * 10 + (s[pos++] - '0');
	    if (s[pos] == '[') {
		Pair p = parse(s, pos + 1);

		pos = p.next + 1; // skip ']'
		for (int i = 0; i < repeats; i++)
		    sb.append(p.s);
	    } else {
		while (pos < s.length && 'a' <= s[pos] && s[pos] <= 'z')
		    sb.append(s[pos++]);
	    }
	}
	return new Pair(sb.toString(), pos);
    }

    public static void main(String[] args) {
	System.out.println(new Solution().decodeString("3[a2[c]]ef"));
    }
}
