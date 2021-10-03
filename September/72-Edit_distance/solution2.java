import java.util.Arrays;

class Solution {

	// (suffix length, word2 suffix length) -> min operations
	int[][] cache;

	public int minDistance(String word1, String word2) {
		cache = new int[word1.length() + 1][word2.length() + 1];

		for (int i = 0; i < word1.length() + 1; i++)
			Arrays.fill(cache[i], -1);
		return f(word1, 0, word2, 0);
	}

	private int f(String word1, int pos1, String word2, int pos2) {
		if (pos1 == word1.length() && pos2 == word2.length())
			return 0;
		else if (pos1 == word1.length())
			return word2.length() - pos2;
		else if (pos2 == word2.length())
			return word1.length() - pos1;

		if (cache[pos1][pos2] >= 0)
			return cache[pos1][pos2];

		int ans1 = f(word1, pos1 + 1, word2, pos2 + 1);
		int ans2, ans3;

		if (word1.charAt(pos1) == word2.charAt(pos2))
			return ans1;
		// result of replace:
		ans1 = ans1 + 1;
		// result of insert:
		ans2 = f(word1, pos1, word2, pos2 + 1) + 1;
		// result of remove:
		ans3 = f(word1, pos1 + 1, word2, pos2) + 1;

		int ans = Integer.min(Integer.min(ans1, ans2), ans3);

		cache[pos1][pos2] = ans;
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().minDistance("dinitrophenylhydrazine",
				"acetylphenylhydrazine"));
	}
}
