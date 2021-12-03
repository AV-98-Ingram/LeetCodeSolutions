import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Solution {
    /**
       Idea: keep track of states.

       The intuition:

       We could aggregate the states along the scan of s1.  A state is
       a position in s2.  When we match a character c in s2, the
       states will be updated.  For example, if c is the first and the
       third character of s2.  We will update state 2 to state 3, if
       state 2 exists and we will add a new state 1.  

       This raw idea is equivalent to brutal force BUT in fact we do
       not have to keep all the states:

       For all the aggregated states corresponding to a same position
       in s2, we only keep one such state that may yield the shortest
       window.  So we in total only keep track of length(s2) states.
       So revisiting the example above, instead of adding a new state
       1, we update the old state 1 to corrspond to the just reached
       character c.  This idea can be explained by another intuitive
       example below:

       Giveb s1: abcabcd  s2: abd.       
       When the scan of s1 scaned the prefix "abcab", using the raw
       idea, we have three states, (0->1), (0->4), and (3->4), that
       are all at state 2 referring to "ab" in s2.  However, as we
       only need the shortest window, we only want to keep (3->4)
       among the three of them.
  
       
     */
	final static LinkedList<Integer> emptyList = new LinkedList<>();

	public String minWindow(String s1, String s2) {
		// states[i]: the largest position in s1 such that it covers
		// s2[0 .. i] as a subsequence:
		int[] states = new int[s2.length()];
		final int len1 = s1.length();
		final int len2 = s2.length();
		Map<Character, LinkedList<Integer>> s2Table = new HashMap<>();

		for (int i = 0; i < len2; i++)
			s2Table.computeIfAbsent(s2.charAt(i), (k) -> new LinkedList<>())
			    .addFirst(i); 
		Arrays.fill(states, -1);

		int start = 0, end = -1;

		for (int i = 0; i < len1; i++) {
			char c = s1.charAt(i);

			// we do not have to iterate over all
			// states. We only iterate over the number of
			// repeats of 'c' in 's2' times.
			for (int s2pos : s2Table.getOrDefault(c, emptyList)) {			    
				if (s2pos > 0)
				     // need to make sure that the
				     // positions of 'c' in 's2' are
				     // is descending order so that
				     // the assignment below will not
				     // read a newly updated state in
				     // this round. That is we update
				     // "states" from
				     // "\old(states)". Shall be
				     // careful to avoid update
				     // "states" from "stares".
					states[s2pos] = states[s2pos - 1];
				else
					states[s2pos] = i;
				if (s2pos == len2 - 1 && states[s2pos] >= 0) {
					if (end < 0 || (i - states[s2pos] < (end - start))) {
						start = states[s2pos];
						end = i;
					}
				}
			}
		}
		if (end >= 0)
			return s1.substring(start, end + 1);
		return "";
	}

	public static void main(String[] args) {
		new Solution().minWindow("cnhczmccqouqadqtmjjzl", "mm");
	}
}

