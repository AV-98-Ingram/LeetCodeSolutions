import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    class Sentences {
	List<String> lst = new LinkedList<>();
    }

    static List<String> emptyList = new LinkedList<>();
    Map<Character, List<String>> table = new HashMap<>();
    Sentences[] results;

    public List<String> wordBreak(String s, List<String> wordDict) {
	for (String w : wordDict)
	    table.computeIfAbsent(w.charAt(w.length() - 1),
				  k -> new LinkedList<>()).add(w);

	char[] chars = s.toCharArray();

	results = new Sentences[chars.length];
	Arrays.fill(results, null);
	for (int i = 0; i < chars.length; i++) {
	    char last = chars[i];

	    results[i] = new Sentences();
	    for (String w : table.getOrDefault(last, emptyList)) {
		int start = i - w.length() + 1;

		if (start >= 0)
		    if (start > 0 && results[start - 1].lst.isEmpty())
			continue;
		    else
			matchAndUpdate(w, chars, start, i);
	    }
	}
	return results[chars.length - 1].lst;
    }

    private void matchAndUpdate(String w, char[] chars, int start, int end) {
	for (int i = start; i <= end; i++)
	    if (w.charAt(i - start) != chars[i])
		return;

	if (start == 0) {
	    results[end].lst.add(w);
	} else {
	    for (String sts : results[start - 1].lst)
		results[end].lst.add(sts + " " + w);
	}
    }
}
