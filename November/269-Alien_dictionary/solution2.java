import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Another idea:
 * 
 * build the graph, where a node n represents a unique letter letter(n) and an
 * edges from node n to node m means that <code> letter(n) < letter(m) </code>.
 * 
 * The graph must be acyclic otherwise we will not be able to solve it.
 * 
 * The second step is to do a topological BFS using Khan's algorithm.
 * 
 * It turns out its slower than my first idea tho.
 * 
 * @author ziqing
 *
 */
class Solution {

    public String alienOrder(String[] words) {
	// get all the edges:
	Map<Character, Set<Character>> edges = new HashMap<>();
	Set<Character> starters = new HashSet<>();
	int incoming[] = new int[26];
	int numLetters;

	// init solution:
	for (String word : words)
	    for (char c : word.toCharArray())
		starters.add(c);
	numLetters = starters.size();
	Arrays.fill(incoming, 0);
	if (buildGraph(words, 0, words.length, 0, edges, starters, incoming)) {
	    // constraint solving:
	    Collection<Character> solution = topologicalSort(starters, incoming,
							     edges);

	    if (solution != null && solution.size() == numLetters) {
		String result = "";

		for (char c : solution)
		    result += c;
		return result;
	    }
	}
	return "";
    }

    /*
     * Given words { "abc", "ac", "bc", "bd"}, first adding edges for all the
     * first letters, i.e., "a", "a", "b", b"; then recursive on the words
     * sharing a prefix, i.e., recursion("abc", "ac") and recursion("bc", "bd")
     * with a pointer to the second letters of each word.
     */
    private boolean buildGraph(final String[] words, int start, int end,
			       int pos, Map<Character, Set<Character>> edges,
			       Set<Character> starters, int[] incoming) {
	if (start >= end)
	    return true;

	int i;
	char w = 0;
	// skip ended prefix:
	for (i = start; i < end; i++)
	    if (words[i].length() > pos)
		break;
	start = i;
	if (start < end) {
	    w = words[start].charAt(pos);
	}
	for (i = start + 1; i < end; i++) {
	    if (words[i].length() <= pos) {
		return false; // invalid input, for example {"abc", "ab"}
	    }

	    char c = words[i].charAt(pos);

	    if (c != w) {
		if (!buildGraph(words, start, i, pos + 1, edges, starters,
				incoming))
		    return false;
		start = i;
		if (edges.computeIfAbsent(w, k -> new HashSet<>()).add(c)) {
		    // w < c:
		    starters.remove(c);
		    incoming[c - 'a']++;
		}
		w = c;
	    }
	}
	return buildGraph(words, start, end, pos + 1, edges, starters,
			  incoming);
    }

    private Collection<Character> topologicalSort(Set<Character> starters,
						  int[] incoming, Map<Character, Set<Character>> edges) {
	LinkedHashSet<Character> out = new LinkedHashSet<>();
	List<Character> nodes = new LinkedList<>(starters);

	while (!nodes.isEmpty()) {
	    char c = nodes.remove(0);
	    Set<Character> nexts = edges.get(c);

	    if (!out.add(c))
		return null; // cycle exists
	    if (nexts == null)
		continue;
	    Iterator<Character> iter = nexts.iterator();
	    while (iter.hasNext()) {
		char w = iter.next();

		iter.remove();
		incoming[w - 'a']--;
		if (incoming[w - 'a'] == 0)
		    nodes.add(w);
	    }
	}
	return out;
    }

    public static void main(String[] args) {
	new Solution().alienOrder(new String[] { "z", "x", "a", "zb", "zx" });
    }
}
