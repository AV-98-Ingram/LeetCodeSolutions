import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    class Trie {
	Map<Character, Trie> children = new HashMap<>(52);
	String word = null;

	Trie add(char c) {
	    return children.computeIfAbsent(c, (k) -> new Trie());
	}
    }

    // optimize with cache:
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
	Arrays.sort(words, (w1, w2) -> (Integer.compare(w1.length(), w2.length())));

	Trie root = new Trie();
	List<String> results = new LinkedList<>();
	Map<String, Integer> cache = new HashMap<>();

	for (String w : words) {
	    int n = concatWord(w, root, true, cache);

	    if (n > 1)
		results.add(w);
	}
	return results;
    }

    // returns number of concatenated segments in this word.  If the
    // word can be concatednated, the returned number is at least 2.
    private int concatWord(String word, Trie root, boolean saveWord, Map<String, Integer> cache) {
	int len = word.length();
	Trie trie = root;

	if (!saveWord) {
	    Integer cached = cache.get(word);

	    if (cached != null)
		return cached;
	}

	for (int i = 0; i < len; i++) {
	    trie = saveWord ? trie.add(word.charAt(i)) : trie.children.get(word.charAt(i));
	    if (trie == null)
		return 0;
	    if (trie.word != null && i < len - 1) {
		int n = concatWord(word.substring(i + 1), root, false, cache);

		if (n > 0) {
		    cache.put(word, n + 1);
		    return n + 1;
		}
	    }
	}
	if (saveWord) {
	    trie.word = word;
	    cache.put(trie.word, 1);
	    return 1;
	} else {
	    return trie.word == null ? 0 : 1;
	}
    }

    public static void main(String[] args) {
	new Solution().findAllConcatenatedWordsInADict(new String[] { "cat", "cats", "catsdogcats", "dog", "dogcatsdog",
								      "hippopotamuses", "rat", "ratcatdogcat" });
    }
}
