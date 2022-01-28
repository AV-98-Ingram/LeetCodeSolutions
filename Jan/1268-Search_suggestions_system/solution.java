import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

class Solution {

    static class Trie {
	Map<Character, Trie> children = new HashMap<>();
	TreeSet<String> ts = new TreeSet<>((a, b) -> (a.compareTo(b)));

	Trie add(char c, String s) {
	    Trie child = children.computeIfAbsent(c, k -> new Trie());

	    child.ts.add(s);
	    return child;
	}
    }

    static final Trie emptyTrie = new Trie();

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
	Trie root = new Trie();

	for (String prod : products) {
	    Trie trie = root;

	    for (char c : prod.toCharArray())
		trie = trie.add(c, prod);
	}

	List<List<String>> ll = new LinkedList<>();
	Trie trie = root;

	for (char c : searchWord.toCharArray()) {
	    List<String> l = new LinkedList<>();
	    int n = 3;

	    trie = trie.children.getOrDefault(c, emptyTrie);

	    while (!trie.ts.isEmpty() && n > 0) {
		l.add(trie.ts.pollFirst());
		n--;
	    }
	    ll.add(l);
	}
	return ll;
    }
}
