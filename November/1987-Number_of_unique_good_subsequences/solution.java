import java.util.HashSet;
import java.util.Set;

class Solution {

    Set<Integer> seen = new HashSet<>();

    public int numberOfUniqueGoodSubsequences(String binary) {
	final String bin = binary;
	boolean hasOne = false;
	String newBin = null;
	int i;

	// get rid of leading zeroes:
	for (i = 0; i < bin.length(); i++) {
	    if (bin.charAt(i) == '1') {
		newBin = bin.substring(i);
		hasOne = true;
		break;
	    }
	}
	if (!hasOne)
	    return 1;
	seen.add(1);
	if (elaborate(newBin, 1, 1) || i > 0)
	    seen.add(0);
	return (int) ((long) seen.size() % (1000000000 + 7));
    }

    private boolean elaborate(String bin, int pos, int val) {
	if (pos == bin.length())
	    return false;

	char c = bin.charAt(pos);
	int newVal = val * 10 + (c - '0');
	boolean hasZero = c == '0';

	if (seen.add(newVal))
	    hasZero = elaborate(bin, pos + 1, newVal) || hasZero;
	hasZero = elaborate(bin, pos + 1, val) || hasZero;
	return hasZero;
    }

    public static void main(String[] args) {
	new Solution().numberOfUniqueGoodSubsequences(
						      "111001101100000001001110110101110001100");
    }
}
