import java.util.Arrays;

class Solution {

	// (suffix length, word2 suffix length) -> min operations
	int[][] cache;

	public int minDistance(String word1, String word2) {
		cache = new int[word1.length() + 1][word2.length() + 1];

		for (int i = 0; i < word1.length() + 1; i++)
			Arrays.fill(cache[i], -1);
		return f(word1, word2, 0);
	}

	private int f(String word1, String word2, int pos) {
		if (pos == word1.length() && pos == word2.length())
			return 0;
		else if (pos == word1.length())
			return word2.length() - pos;
		else if (pos == word2.length())
			return word1.length() - pos;

		int cached = cache[word1.length() - pos][word2.length() - pos];

		if (cached >= 0)
			return cached;

		char c1 = word1.charAt(pos);
		char c2 = word2.charAt(pos);
		int nops1, nops2, nops3;

		if (c1 == c2)
			return f(word1, word2, pos + 1);
		// replace c1 with c2:
		String word11 = word1.substring(0, pos) + c2 + word1.substring(pos + 1);

		nops1 = f(word11, word2, pos + 1) + 1;

		// insert before c1:
		String word12 = word1.substring(0, pos) + c2 + word1.substring(pos);

		nops2 = f(word12, word2, pos + 1) + 1;

		// remove c1
		String word13 = word1.substring(0, pos) + word1.substring(pos + 1);

		if (pos < word13.length() && word13.charAt(pos) == c2)
			nops3 = f(word13, word2, pos + 1) + 1;
		else
			nops3 = f(word13, word2, pos) + 1;

		int min = Integer.min(Integer.min(nops1, nops2), nops3);

		cache[word1.length() - pos][word2.length() - pos] = min;
		return min;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().minDistance("dinitrophenylhydrazine",
				"acetylphenylhydrazine"));
	}
}
