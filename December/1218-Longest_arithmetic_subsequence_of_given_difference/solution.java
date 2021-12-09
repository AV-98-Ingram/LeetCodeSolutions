import java.util.HashMap;
import java.util.Map;

class Solution {
    /*
     * map(i): the max subseq ending with this 'i' if the next number is i
     */
    public int longestSubsequence(int[] arr, int difference) {
	Map<Integer, Integer> whatsNext = new HashMap<>();
	int max = 0;

	for (int i : arr) {
	    Integer len = whatsNext.get(i);
	    int next = i + difference;
	    if (len == null) {
		// 2 is the smallest value in whatsNext so no need to
		// compare with existing ones
		whatsNext.putIfAbsent(next, 2);
		len = 1;
	    } else {
		final int nextLen = len + 1;
		if (null == whatsNext.computeIfPresent(next, (k, v) -> (Math.max(v, nextLen))))
		    whatsNext.put(next, nextLen);
	    }
	    if (len > max)
		max = len;
	}
	return max;
    }

    public static void main(String[] args) {
	new Solution().longestSubsequence(new int[] { 1, 5, 7, 8, 5, 3, 4, 2, 1 }, -2);
    }
}
