import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    class Trie {
	Map<Character, Trie> children = new HashMap<>();
	List<Integer> indices = new LinkedList<>();
	List<Integer> owners = new LinkedList<>();

	Trie addChar(char c) {
	    return children.computeIfAbsent(c, k -> new Trie());
	}
    }

    public List<List<Integer>> palindromePairs(String[] words) {
	Trie reRoot = new Trie(); // reversed words
	int i = 0;
	List<List<Integer>> result = new LinkedList<>();
	
	for (String w : words) {
	    final int len = w.length();
	    Trie trie = reRoot;
	    
	    trie.owners.add(i);
	    for (int c = len - 1; c >= 0; c--) {
		trie = trie.addChar(w.charAt(c));
		trie.owners.add(i);
	    }
	    trie.indices.add(i);
	    i++;
	}
	i = 0;
	for (String w : words) {
	    final int len = w.length();
	    Trie trie = reRoot;
	    
	    // words reverse
	    for (int k = 0; k < len; k++) {
		for (int idx : trie.indices) {
		    // w[0 .. k] matches whole reversed words[idx]:
		    if (check(w, k, w.length()))
			result.add(Arrays.asList(i, idx));
		}
		trie = trie.children.get(w.charAt(k));
		if (trie == null)
		    break;
	    }
	    if (trie != null) {
		for (int owner : trie.owners) {
		    if (i == owner)
			continue;
		    // reversed words[owner] prefix matches whole w
		    if (check(words[owner], 0, words[owner].length() - len))
			result.add(Arrays.asList(i, owner));
		}
	    }
	    i++;
	}
	return result;
    }

    private boolean check(String w, int start, int end) {
	int r = end - 1;
	int l = start;

	while (l < r) {
	    if (w.charAt(l) != w.charAt(r))
		return false;
	    l++;
	    r--;
	}
	return true;
    }

    public static void main(String[] args) {
	new Solution().palindromePairs(new String[] { "abcd", "dcba", "lls", "s", "sssll", "" });
    }
}
