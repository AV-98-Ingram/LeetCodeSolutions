import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
	/**
	 * Idea: DP. We compute dp(i,j), which represents the number of turns needed
	 * to print the substring s[i .. j].
	 * 
	 * First, lets walk through an example: "bcbada". Suppose we have computed
	 * all dp(i, j) for "0<= i,j < 5". Now we compute dp(i, 5) for 0 <= i < 6:
	 * <code>
	 *   dp(5, 5) is trivially 1;
	 *   dp(4, 5) is 2 as 2 turns needed for "da";
	 *   dp(3, 5) is 2 as we first print "aaa" then "ada";  
	 *   -- Let p("c x ... y c") be the number of turns to print the string "c x ... y c". 
	 *   -- We have p("c x ... y c") = p("x ... y c")
	 *   dp(2, 5) is 3 as we need print 'b' separately then print "ada" which takes dp(3, 5)
	 *   dp(1, 5) is 4 as we need print 'c' separately then print "bada" which takes dp(2, 5)
	 *   dp(0, 5) is non-trivial:  
	 *   -- There are 2 cases as there are two 'b' characters in s[0 .. 5] which may share one turn.
	 *   -- 1 + dp(1, 5) for the first b does not share a turn with the second b
	 *   -- dp(0, 2) + dp(3, 5) for reusing the computed result of s[0 .. 2] where covers both 'b's.
	 *   -- we select the case producing the minimal result.
	 * </code>
	 * 
	 * The generalized algorithm: <code>
	 * for (int i: 0 .. s.length()-1) {
	 *   dp(i, i) = 1;
	 *   for (int j: 0 .. i-1) {
	 *     dp(j, i) = s[j] == s[i] ? dp(j+1, i) :
	 *                               min(dp(j + 1, i) + 1,
	 *                                   { dp(j, k) + dp(k+1, i) | j < k < i /\ s[k] == s[j] });
	 *   }
	 * }  
	 * </code>
	 */
	public int strangePrinter(String s) {
		Map<Character, List<Integer>> table = new HashMap<>();
		int[][] dp = new int[s.length()][s.length()];
		final int len = s.length();

		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);

			dp[i][i] = 1;
			// Optimization: only to keep the largest index for a consecutive of
			// repeated ones. Why largest? Because when we use "table", we are
			// looking for appearances of a character larger than some index j.
			// Keeping the largest makes sure that the one representing all
			// is larger than j if any of the representees is ever larger than
			// j.
			if (i == len - 1 || s.charAt(i + 1) != c)
				table.computeIfAbsent(c, (k) -> new LinkedList<>()).add(i);
			for (int j = i - 1; j >= 0; j--) {
				char cc = s.charAt(j);

				if (cc == c || cc == s.charAt(j + 1))
					dp[j][i] = dp[j + 1][i];
				else {
					int min = dp[j + 1][i] + 1;

					for (int idx : table.get(cc)) {
						if (idx <= j)
							continue;

						int turns = dp[j][idx] + dp[idx + 1][i];

						min = Math.min(min, turns);
					}
					dp[j][i] = min;
				}
			}
		}
		return dp[0][len - 1];
	}

	public static void main(String[] args) {
		new Solution().strangePrinter("babcbada");
	}
}
