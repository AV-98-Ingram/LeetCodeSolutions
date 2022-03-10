import java.util.*;

class Solution {
    public int[] recoverArray(int n, int[] sums) {
	int[] ret = new int[n];
	ArrayList<Integer> l = new ArrayList<>(n);

	for (int num : sums)
	    l.add(num);
	find(l, ret, n);
	return ret;
    }

    private boolean find(ArrayList<Integer> sums, int output[], int n) {
	if (n == 0)
	    return true;

	Collections.sort(sums);

	Map<Integer, Integer> table = new HashMap<>();
	Map<Integer, Integer> table2 = table;
	int size = sums.size();
	int x = sums.get(size - 1) - sums.get(size - 2);

	for (int num : sums)
	    table.merge(num, 1, Integer::sum);

	boolean hasMinusX = table.containsKey(-x);

	if (hasMinusX)
	    table2 = new HashMap<>(table);
	if (table.containsKey(x)) {
	    ArrayList<Integer> excluding = new ArrayList<>(size / 2 + 1);

	    for (int num : sums) {
		Integer count = table.computeIfPresent(num, (k, v) -> v - 1);

		if (count >= 0) {
		    count = table.computeIfPresent(num + x, (k, v) -> v - 1);
		    if (count != null && count >= 0)
			excluding.add(num);
		    else
			return false;
		}
	    }
	    output[n - 1] = x;
	    if (find(excluding, output, n - 1))
		return true;
	}
	if (hasMinusX) {
	    table = table2;

	    ArrayList<Integer> excluding = new ArrayList<>(size / 2 + 1);

	    for (int i = size - 1; i >= 0; i--) {
		int num = sums.get(i);
		Integer count = table.computeIfPresent(num, (k, v) -> v - 1);

		if (count >= 0) {
		    count = table.computeIfPresent(num - x, (k, v) -> v - 1);
		    if (count != null && count >= 0)
			excluding.add(num);
		    else
			return false;
		}
	    }
	    output[n - 1] = -x;
	    if (find(excluding, output, n - 1))
		return true;
	}
	return false;
    }

    public static void main(String[] args) {
	new Solution().recoverArray(4,
				    // 305, -381, -381, 579
				    new int[] { 305, -76, -381, 0, -457, -183, -762, -381, 503, 198, 884, 579, 198, 122, -76, 503 });
    }
}
