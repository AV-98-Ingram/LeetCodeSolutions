import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    class ListNode {
	String val = null;
	ListNode prev = null;

	ListNode(String val) {
	    this.val = val;
	}
    }

    public List<List<String>> findLadders(String beginWord, String endWord,
					  List<String> wordList) {
	Map<String, String> dict = new HashMap<>();

	for (String s : wordList)
	    dict.put(s, s);
	if (dict.put(endWord, endWord) == null)
	    return new LinkedList<>();
	dict.put(beginWord, beginWord);

	List<List<String>> results = new LinkedList<>();
	List<ListNode> queue = new LinkedList<>();
	List<ListNode> nxtQue = new LinkedList<>();
	boolean stop = false;

	dict.remove(beginWord);
	queue.add(new ListNode(beginWord));
	while (!queue.isEmpty()) {
	    while (!queue.isEmpty()) {
		ListNode node = queue.remove(0);

		if (node.val == endWord) {
		    stop = true;
		    results.add(getChain(node));
		}
		if (stop)
		    continue;

		char[] chArr = node.val.toCharArray();

		for (int i = 0; i < chArr.length; i++) {
		    char c = chArr[i];

		    for (char cc = 'a'; cc <= 'z'; cc++) {
			if (cc == c)
			    continue;
			chArr[i] = cc;

			String nxt = dict.get(new String(chArr));

			if (nxt != null) {
			    ListNode nxtNode = new ListNode(nxt);

			    nxtNode.prev = node;
			    nxtQue.add(nxtNode);
			}
			chArr[i] = c;
		    }
		}
	    }
	    List<ListNode> tmp = queue;
	    queue = nxtQue;
	    nxtQue = tmp;
	    for (ListNode n : queue)
		dict.remove(n.val);
	}
	return results;
    }

    private List<String> getChain(ListNode node) {
	List<String> ret = new LinkedList<>();

	while (node != null) {
	    ret.add(0, node.val);
	    node = node.prev;
	}
	return ret;
    }

    public static void main(String[] args) {
	new Solution().findLadders("red", "tax", Arrays.asList(new String[] {
		    "ted", "tex", "red", "tax", "tad", "den", "rex", "pee" }));
    }
}
