import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {

    public int longestStrChain(String[] words) {
	PriorityQueue<List<String>> pq = new PriorityQueue<>(
							     (l1, l2) -> (Integer.compare(l1.get(0).length(), l2.get(0).length())));
	Map<Integer, List<String>> holder = new HashMap<>();

	for (String w : words)
	    holder.computeIfAbsent(w.length(), k -> new LinkedList<>()).add(w);
	for (List<String> l : holder.values())
	    pq.add(l);

	int maxLen = 1;
	int currLen = 1;

	while (!pq.isEmpty()) {
	    PriorityQueue<List<String>> tmp = new PriorityQueue<>(pq.comparator());

	    List<String> curr = pq.poll();
	    while (!pq.isEmpty()) {
		List<String> next = pq.poll();

		curr = computeSuccessors(curr, next);
		if (!next.isEmpty())
		    tmp.add(next);
		if (curr.isEmpty())
		    break;
		currLen++;
	    }
	    if (currLen > maxLen)
		maxLen = currLen;
	    currLen = 1;
	    pq.addAll(tmp);
	}
	return currLen > maxLen ? currLen : maxLen;
    }

    private List<String> computeSuccessors(List<String> predors, List<String> sussors) {
	List<String> ret = new LinkedList<>();

	for (String p : predors) {
	    Iterator<String> iter = sussors.iterator();

	    while (iter.hasNext()) {
		String s = iter.next();

		if (isSuccessor(p, s)) {
		    ret.add(s);
		    iter.remove();
		}
	    }
	    if (sussors.isEmpty())
		break;
	}
	return ret;
    }

    private boolean isSuccessor(String p, String s) {
	final int pLen = p.length();
	int i = 0;

	for (; i < pLen; i++)
	    if (p.charAt(i) != s.charAt(i))
		break;
	for (; i < pLen; i++)
	    if (p.charAt(i) != s.charAt(i + 1))
		return false;
	return true;
    }

    public static void main(String[] args) {
	new Solution().longestStrChain(new String[] { "czgxmxrpx", "lgh", "bj", "cheheex", "jnzlxgh", "nzlgh",
						      "ltxdoxc", "bju", "srxoatl", "bbadhiju", "cmpx", "xi", "ntxbzdr", "cheheevx", "bdju", "sra", "getqgxi",
						      "geqxi", "hheex", "ltxdc", "nzlxgh", "pjnzlxgh", "e", "bbadhju", "cmxrpx", "gh", "pjnzlxghe", "oqlt",
						      "x", "sarxoatl", "ee", "bbadju", "lxdc", "geqgxi", "oqltu", "heex", "oql", "eex", "bbdju", "ntxubzdr",
						      "sroa", "cxmxrpx", "cmrpx", "ltxdoc", "g", "cgxmxrpx", "nlgh", "sroat", "sroatl", "fcheheevx", "gxi",
						      "gqxi", "heheex" });
    }
}
