import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

class Solution {
    /*
     * Greedy
     */
    public String removeDuplicateLetters(String s) {
	TreeSet<Integer> lastAppears = new TreeSet<>();
	Map<Character, LinkedList<Integer>> appears = new HashMap<>();
	final int len = s.length();

	for (int i = 0; i < len; i++)
	    appears.computeIfAbsent(s.charAt(i), k -> new LinkedList<>()).add(i);
	for (LinkedList<Integer> l : appears.values())
	    lastAppears.add(l.getLast());

	PriorityQueue<Character> greedyQueue = new PriorityQueue<>();
	StringBuffer sb = new StringBuffer();
	int pos = 0;

	greedyQueue.addAll(appears.keySet());
	while (!greedyQueue.isEmpty()) {
	    List<Character> addBack = new LinkedList<>();

	    while (!greedyQueue.isEmpty()) {
		char c = greedyQueue.poll();
		boolean cIsGood = false;

		for (int appear : appears.get(c))
		    if (appear >= pos && appear <= lastAppears.first()) {
			pos = appear;
			sb.append(c);
			lastAppears.remove(appears.get(c).getLast());
			cIsGood = true;
			greedyQueue.addAll(addBack);
			break;
		    }
		if (!cIsGood)
		    addBack.add(c);
		else
		    break;
	    }
	}
	return sb.toString();
    }

    public static void main(String[] args) {
	new Solution().removeDuplicateLetters("leetcode");
    }
}
