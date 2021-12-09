import java.util.HashMap;
import java.util.Map;

class Solution {

    public boolean canReorderDoubled(int[] arr) {
	// table[i]: the number of appearances of number i in 'arr':
	Map<Integer, Integer> table = new HashMap<>();

	for (int i : arr)
	    table.merge(i, 1, (a, b) -> (a + b));

	boolean matched = false;
	int num = table.keySet().iterator().next();

	do {
	    int pairee = num << 1;
	    Integer count = table.computeIfPresent(pairee, (k, v) -> v - 1);

	    if (count == null && (num & 1) == 0) {
		pairee = num >> 1;
		count = table.computeIfPresent(pairee, (k, v) -> v - 1);
	    }
	    if (count == null) {
		matched = false;
		break;
	    } else
		matched = true;
	    if (count == 0)
		table.remove(pairee);
	    count = table.computeIfPresent(num, (k, v) -> v - 1);
	    if (count == null) {
		matched = false;
		break;
	    } else
		matched = true;
	    if (count == 0) {
		table.remove(num);
		if (!table.isEmpty())
		    num = table.keySet().iterator().next();
	    }
	} while (!table.isEmpty());
	return matched;
    }

    public static void main(String[] args) {
	new Solution().canReorderDoubled(new int[] { 33, 0 });
    }
}
