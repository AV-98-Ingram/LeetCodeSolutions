import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Simple window moving brutal force solution with tiny little optimization.
 * (The pure brutal force solution timeouts on LeetCode.)
 * 
 * The window size is "winSize = nwords * sizeof(word)". We move the window to
 * one character right per step check if the window is a concatenation of the
 * words. The tiny optimization here is
 * 
 * <code>
 *   if the current window [i .. i + winSize) is matched, we can continue to try 
 *   the window [i + sizeof(word) .. i + winSize + sizeof(word)) in advance with 
 *   the knowledge that the first (nwords-1) words in the window have matched.
 * </code>
 * 
 * With this tiny optimization, we still need to move window to the right step
 * by step, but we could skip the windows that have been tried in advance.
 * 
 * @author ziqing
 *
 */
class Solution {

	private Set<Integer> visitedHeads = new HashSet<>();

	private class State {
		LinkedHashSet<Integer> matcheds = new LinkedHashSet<>();

		boolean add(int match) {
			return matcheds.add(match);
		}

		// pre-cond: matcheds.size >= 1
		void removeFirst() {
			Integer first = matcheds.iterator().next();

			matcheds.remove(first);
		}
	}

	public List<Integer> findSubstring(String s, String[] words) {
		if (words.length == 0)
			return new LinkedList<>();

		State state = new State();

		return findSubstringWorker(s, 0, words, state);
	}

	private List<Integer> findSubstringWorker(String str, int start,
			String[] words, State state) {
		int strSize = str.length();
		int wordSize = words[0].length();
		// the minimal string length needed to match from start:
		int min = start + (words.length - state.matcheds.size()) * wordSize;
		List<Integer> results = new LinkedList<>();
		int head = start - (state.matcheds.size() * wordSize);

		if (strSize < min)
			return results;
		if (visitedHeads.contains(head))
			return results;

		boolean unmatch = false;
		int i = start;

		while (state.matcheds.size() < words.length) {
			int w = nextWord(str, i, words, state);

			if (w >= 0) {
				if (state.add(w)) {
					i += wordSize;
					continue;
				} else {
					unmatch = true;
					break;
				}
			}
			unmatch = true;
			break;
		}
		visitedHeads.add(head);
		if (!unmatch) {
			results.add(head);
			state.removeFirst();
			if (words.length > 1)
				results.addAll(findSubstringWorker(str,
						head + wordSize * (words.length - 1), words, state));
		}
		results.addAll(findSubstringWorker(str, head + 1, words, new State()));
		return results;
	}

	private int nextWord(String str, int start, String[] words, State state) {
		int ret = -1;
		int firstRet = -1;
		boolean useRet = false;

		for (int w = 0; w < words.length; w++)
			if (str.startsWith(words[w], start)) {
				ret = w;
				if (firstRet < 0)
					firstRet = w;
				if (state.matcheds.contains(w))
					continue;
				useRet = true;
				break;
			}
		return useRet ? ret : firstRet;
	}

    public static void main(String[] args) {
	String s = "yzwaaaxyzwaaaxyz";
	String[] words = new String[] { "yzw", "aaa", "xyz" };
	// String s = "wordgoodgoodgoodbestword";
	// String[] words = new String[] { "word","good","best","good"};
	// String s = "barfoofoobarthefoobarman";
	// String[] words = new String[] { "bar","foo","the" };
	// String s = "a";
	// String[] words = new String[] { "a" };
	// String s = "bcabbcaabbccacacbabccacaababcbb";
	// String[] words = new String[] { "c", "b", "a", "c", "a", "a", "a",
	// "b",
	// "c" };
	
	for (int r : new Solution().findSubstring(s, words)) {
	    System.out.printf("%3d", r);
	}
    }
}
