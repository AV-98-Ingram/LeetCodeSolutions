import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
	if (k == 0)
	    return 0;

	int l = 0, r = 0; // l inclusive, r exclusive
	final int len = s.length();
	int numUniq = 0;
	Map<Character, Integer> table = new HashMap<>();
	int max = 0;

	while (r < len) { // invariant: numUniq <= k
	    char c = s.charAt(r++);

	    if (table.getOrDefault(c, 0) == 0)
		numUniq++;
	    table.merge(c, 1, Integer::sum);
	    if (numUniq > k) {
		// record the window size:
		max = Math.max(max, r - l - 1);
		// Shrink the window until numUniq == k.
		// Note that we cannot shrink the window to numUniq < k. This will cause a miss
		// of a possibly larger window when characters pointed by 'r' and after 'r' are
		// all duplicated ones.
		while (l < r && numUniq > k) {
		    c = s.charAt(l++);
		    if (table.computeIfPresent(c, (key, v) -> v - 1) == 0)
			numUniq--;
		}
	    }
	}
	max = Math.max(max, r - l);
	return max;
    }

    public static void main(String[] args) {
	new Solution().lengthOfLongestSubstringKDistinct("abaccc", 2);
    }
}
