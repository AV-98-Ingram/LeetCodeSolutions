import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

    public int numSplits(String s) {
	Map<Character, Integer> rightChars = new HashMap<>();
	int l = 0;
	final int len = s.length();

	for (int i = 1; i < len; i++)
	    rightChars.merge(s.charAt(i), 1, (v1, v2) -> v1 + v2);

	Set<Character> leftChars = new HashSet<>();
	int result = 0;

	leftChars.add(s.charAt(0));
	for (int i = 1; i < len; i++) {
	    if (leftChars.size() == rightChars.size())
		result++;
	    char c = s.charAt(i);
	    Integer newVal;

	    leftChars.add(c);
	    newVal = rightChars.computeIfPresent(c, (k, v) -> v - 1);
	    if (newVal != null && newVal == 0)
		rightChars.remove(c);
	}
	return result;
    }
}

