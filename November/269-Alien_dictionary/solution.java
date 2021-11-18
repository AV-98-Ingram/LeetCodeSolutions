import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Basic idea:
 * 
 * build the graph, where a node n represents a unique letter letter(n) and an
 * edges from node n to node m means that <code> letter(n) < letter(m) </code>.
 * 
 * The graph must be acyclic otherwise we will not be able to solve it.
 * 
 * The second step is to solve the constraints. That it, for each pair of
 * letters, figure out how does compare to the other. This can be done through
 * DFS with cycle detection otherwise it could go infinity.
 * 
 * Finally, just use the solution to sort the letters.
 * 
 * @author ziqing
 *
 */
class Solution {
    static final Set<Character> emptyCollection = new TreeSet<>();

    public String alienOrder(String[] words) {
	// get all the edges:
	final boolean solution[][] = new boolean[26][26];
	Map<Character, Set<Character>> edges = new HashMap<>();
	Set<Character> letterSet = new HashSet<>();

	// init solution:
	for (int i = 0; i < 26; i++)
	    Arrays.fill(solution[i], false);
	if (buildGraph(words, 0, words.length, 0, edges, letterSet))
	    // constraint solving:
	    if (constraintSolving(letterSet, edges, solution)) {
		// sort the letters:
		char[] letters = new char[letterSet.size()];
		int i = 0;

		for (char c : letterSet)
		    letters[i++] = c;
		sort(letters, solution);
		return new String(letters);
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
			       Set<Character> letterSet) {
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
	    letterSet.add(w);
	}
	for (i = start + 1; i < end; i++) {
	    if (words[i].length() <= pos) {
		return false; // invalid input, for example {"abc", "ab"}
	    }

	    char c = words[i].charAt(pos);

	    letterSet.add(c);
	    if (c != w) {
		if (!buildGraph(words, start, i, pos + 1, edges, letterSet))
		    return false;
		start = i;
		edges.computeIfAbsent(w, k -> new HashSet<>()).add(c); // w < c
		w = c;
	    }
	}
	return buildGraph(words, start, end, pos + 1, edges, letterSet);
    }

    // for each pair of letters a and b, figure out whether a > b, a < b or a =
    // b (unknown) using DFS with cycle detection of the graph:
    private boolean constraintSolving(Iterable<Character> letters,
				      Map<Character, Set<Character>> edges, boolean out[][]) {
	for (char c : letters) {
	    LinkedList<Character> stack = new LinkedList<>();
	    Set<Character> visited = new HashSet<>();
	    Set<Character> onStack = new HashSet<>();

	    stack.add(c);
	    while (!stack.isEmpty()) {
		Character w = stack.remove(0);

		onStack.remove(w);
		visited.add(w);
		if (!w.equals(c)) {
		    out[c - 'a'][w - 'a'] = true;
		    if (out[w - 'a'][c - 'a'])
			return false; // cycle detected
		}
		for (Character nxt : edges.getOrDefault(w, emptyCollection)) {
		    if (!visited.contains(nxt)) {
			stack.addFirst(nxt);
			onStack.add(nxt);
		    } else if (onStack.contains(nxt))
			return false;// cycle detected
		}
	    }
	}
	return true;
    }

    // Java's Arrays.sort or Collections.sort seems buggy, so I use this O(n^2)
    // sort
    private void sort(char[] a, boolean[][] comparator) {
	final int ub = a.length;

	for (int i = 0; i < ub; i++)
	    for (int j = i + 1; j < ub; j++) {
		if (comparator[a[j] - 'a'][a[i] - 'a']) {
		    char tmp = a[j];

		    a[j] = a[i];
		    a[i] = tmp;
		}
	    }
    }

    public static void main(String[] args) {
	new Solution().alienOrder(new String[] { "vlxpwiqbsg", "cpwqwqcd", });
    }
}
