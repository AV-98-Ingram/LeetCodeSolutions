import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s and a dictionary dict. Let f(i) represent if string <code>
 * s[0 .. i]</code> can be word break with dict. To compute f(i), we could try
 * all words in the dict that ends with character s[i]. For each such word w in
 * dict, f(i) is true iff <code>
 * s[i - |w| + 1 .. i] matches w, and
 * f(i - |w|) is true.
 * </code>
 * 
 * @author ziqing
 *
 */
class Solution {

    static List<String> emptyList = new LinkedList<>();
    Map<Character, List<String>> dictByLastChar = new HashMap<>();
    Set<String> dict = new HashSet<>();
    boolean result[];

    public boolean wordBreak(String s, List<String> wordDict) {
	for (String w : wordDict) {
	    dictByLastChar.computeIfAbsent(w.charAt(w.length() - 1),
					   k -> new LinkedList<>()).add(w);
	    dict.add(w);
	}

	char[] chars = s.toCharArray();

	result = new boolean[chars.length];
	for (int i = 0; i < chars.length; i++) {
	    Character last = Character.valueOf(chars[i]);

	    result[i] = false;
	    for (String w : dictByLastChar.getOrDefault(last, emptyList)) {
		int start = i - w.length() + 1;

		if (start >= 0) {
		    if (start > 0 && !result[start - 1])
			continue;
		    if (match(w, chars, start, i)) {
			result[i] = true;
			break;
		    }
		}
	    }
	}
	return result[chars.length - 1];
    }

    private boolean match(String w, char[] chars, int start, int end) {
	for (int i = start; i <= end; i++)
	    if (chars[i] != w.charAt(i - start))
		return false;
	return true;
    }

    public static void main(String[] args) {
	new Solution().wordBreak("ccaccc",
				 Arrays.asList(new String[] { "cc", "ac" }));
    }
}
