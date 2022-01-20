import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lenLongestFibSubseq(int[] arr) {
	final int len = arr.length;
	int tails[][] = new int[len][len]; // tails[i][j]: max Fibo-like subseq ending with arr[i] and arr[j]
	Map<Integer, Integer> table = new HashMap<>(); // map: arr[i] -> i
	int max = 0;

	for (int i = 0; i < len; i++)
	    table.put(arr[i], i);
	for (int i = 0; i < len; i++)
	    for (int j = i + 1; j < len; j++) {
		int next = arr[i] + arr[j];
		int nextIdx = table.getOrDefault(next, -1);

		if (tails[i][j] == 0)
		    tails[i][j] = 2;
		if (nextIdx > j) {
		    tails[j][nextIdx] = Math.max(tails[i][j] + 1, tails[j][nextIdx]);
		    max = Math.max(max, tails[j][nextIdx]);
		}
	    }
	return max;
    }
}

