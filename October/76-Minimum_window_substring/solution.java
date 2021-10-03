/**
 * <p>
 * The high-level idea of this problem is easy to understand: Using two pointers
 * "start" and "end" to maintain the window. Both pointers are initially zero.
 * Moving "end" until found the first proper window. Then move "start" to shrink
 * the window to the minimum size. Compare and set the current min with the
 * window's size. Then set "start" to "start +1", remove "s[start]" from the
 * state, and continue to move "end" for the next proper window. The search is
 * done after "end" passes the end of "s". The two pointers iterate "s" once,
 * respectively, so the time complexity is O(n).
 * </p>
 * 
 * <p>
 * The real trick here is to maintain the state. Let state be a map such that
 * map[c] is the number of the letter 'c' that are needed to match "t" for the
 * current window "[start .. end]". REMARK that map[c] can go negative meaning
 * that there are duplicated 'c's in the window. After a proper window being
 * found, the "map" satisfies the property: for a letter 'c' requited by 't', it
 * must be the case that <code>map[c] <= 0 </code> otherwise the window is not
 * proper. Then we move "start". For every "s[start]" during the moving, if
 * <code>map[s[start]] < 0 </code>, we increase "map[s[start]]" by one, meaning
 * that a duplicate is saw and is skipped. If "s[start] == 0", meaning the first
 * necessary letter is reached by "start", the shirnking is done. The next round
 * of moving end will start with increasing map[start] by one and updating
 * "start" by "start + 1".
 * </p>
 */
class Solution {
	private int min, minStart, count;
	/**
	 * a fixed table, for telling if a letter 'c' is in 't'
	 */
	private int[] tArr;
	/**
	 * the state; state[c] stores the number of 'c's needed to match 't';
	 * state[c] could be negative to indicate duplication.
	 */
	private int[] state;

	public String minWindow(String s, String t) {
		min = s.length() + 1;
		minStart = 0;
		count = t.length();
		tArr = new int[128];
		state = new int[128];

		for (char c : t.toCharArray())
			tArr[c]++;
		System.arraycopy(tArr, 0, state, 0, 128);

		char[] sArr = s.toCharArray();
		int start = 0, end = 0;

		while (start < sArr.length && end < sArr.length) {
			char c = sArr[end];

			if (tArr[c] > 0 && --state[c] >= 0)
				count--; // found a match that is not a duplicated one
			while (count == 0) {
				// found a proper window, now shrinking by moving "start"
				c = sArr[start];
				// move the "start" pointer:
				if (tArr[c] > 0)
					if (state[c] == 0) {
						if (end - start + 1 < min) { // compare and update min
							min = end - start + 1;
							minStart = start;
						}
						// start to search the next proper window with the
						// current shrinked window, where the first matched
						// letter is excluded.
						state[c]++;
						count++;
					} else
						state[c]++;
				start++;
			}
			end++;
		}
		if (min <= s.length())
			return s.substring(minStart, minStart + min);
		return "";
	}

	public static void main(String[] args) {
		/*
		 * "ADOBECODEBANC", "ABC" "acbdbaab", "aabd"
		 */
		System.out.println(new Solution().minWindow("bbaac", "aba"));
		System.out.println(new Solution().minWindow("bba", "ab"));
		System.out.println(new Solution().minWindow("ADOBECODEBANC", "ABC"));
		System.out.println(new Solution().minWindow("acbdbaab", "aabd"));
		System.out.println(new Solution().minWindow("a", "a"));
		System.out.println(new Solution().minWindow("a", "aa"));
		System.out.println(new Solution().minWindow("ab", "b"));
	}
}
