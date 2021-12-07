import java.util.LinkedList;

class Solution {

	class SuffixList {
		// each element is a pair (stringid, position):
		LinkedList<int[]> l = new LinkedList<>();
	}

	public int numMatchingSubseq(String s, String[] words) {
		SuffixList[] bucket = new SuffixList[26];

		for (int i = 0; i < 26; i++)
			bucket[i] = new SuffixList();
		for (int i = 0; i < words.length; i++)
			bucket[words[i].charAt(0) - 'a'].l.add(new int[] { i, 0 });

		final int len = s.length();
		int nwords = 0;

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			SuffixList l = bucket[c - 'a'];
			final int size = l.l.size();

			for (int j = 0; j < size; j++) {
				int[] suffix = l.l.removeFirst();

				suffix[1]++;
				if (suffix[1] == words[suffix[0]].length())
					nwords++;
				else
					bucket[words[suffix[0]].charAt(suffix[1]) - 'a'].l
							.add(suffix);
			}
		}
		return nwords;
	}

	public static void main(String[] args) {
		new Solution().numMatchingSubseq("abcde",
				new String[] { "a", "bb", "acd", "ace" });
	}
}
