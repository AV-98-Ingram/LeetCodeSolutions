import java.util.Arrays;
import java.util.BitSet;

class Solution {
    /*
     * Guided backtrack, too slow.
     */
    public String removeDuplicateLetters(String s) {
	int[] lastAppears = new int[26];
	BitSet uniques = new BitSet(26);
	final int len = s.length();

	Arrays.fill(lastAppears, -1);
	for (int i = 0; i < len; i++) {
	    char c = s.charAt(i);

	    if (lastAppears[c - 'a'] >= 0) {
		uniques.clear(c - 'a');
	    } else
		uniques.set(c - 'a');
	    lastAppears[c - 'a'] = i;
	}

	return guidedBacktrack(s, 0, new StringBuffer(), new BitSet(26), lastAppears, uniques);
    }

    private String guidedBacktrack(String s, int pos, StringBuffer curr, BitSet currLetters, int[] lastAppears,
				   final BitSet uniqs) {
	if (pos == s.length())
	    return curr.toString();

	char c = s.charAt(pos);
	String ret;

	if (uniqs.get(c - 'a')) {
	    currLetters.set(c - 'a');
	    ret = guidedBacktrack(s, pos + 1, curr.append(c), currLetters, lastAppears, uniqs);
	    currLetters.clear(c - 'a');
	    curr.deleteCharAt(curr.length() - 1);
	} else if (currLetters.get(c - 'a')) {
	    return guidedBacktrack(s, pos + 1, curr, currLetters, lastAppears, uniqs);
	} else if (lastAppears[c - 'a'] == pos) {
	    currLetters.set(c - 'a');
	    ret = guidedBacktrack(s, pos + 1, curr.append(c), currLetters, lastAppears, uniqs);
	    currLetters.clear(c - 'a');
	    curr.deleteCharAt(curr.length() - 1);
	} else {
	    String opt1, opt2;

	    currLetters.set(c - 'a');
	    opt1 = guidedBacktrack(s, pos + 1, curr.append(c), currLetters, lastAppears, uniqs);
	    curr.deleteCharAt(curr.length() - 1);
	    currLetters.clear(c - 'a');
	    opt2 = guidedBacktrack(s, pos + 1, curr, currLetters, lastAppears, uniqs);
	    if (opt1.compareTo(opt2) > 0)
		return opt2;
	    return opt1;
	}
	return ret;
    }

    public static void main(String[] args) {
	new Solution().removeDuplicateLetters("bcabc");
    }
}
