import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    // map from sum to their repeats number;
    // read-only once built;
    Map<Integer, Integer> map = new HashMap<>();

    private int theSum;

    public int[] recoverArray(int n, int[] sums) {
	int sum = 0;
	int np = 1;

	for (int i = 0; i < n - 1; i++)
	    np *= 2;
	for (int x : sums) {
	    sum += x;
	    map.merge(x, 1, Integer::sum);
	}
	theSum = sum / np;
	// next, to find any n elements in sums such that their sum is "theSum":
	Arrays.sort(sums);

	int[] arr = new int[n];
	int i = 0;

	for (List<Integer> candidates : pickN(0, n, sums, 0)) {
	    boolean failed = false;

	    i = 0;
	    for (Integer cand : candidates)
		arr[i++] = cand;
	    for (int k = 1; k <= n; k++)
		if (!testN(arr, new HashMap<>(), 0, k, 0)) {
		    failed = true;
		    break;
		}
	    if (failed)
		continue;
	    return arr;
	}
	return null;
    }

    private List<List<Integer>> pickN(int start, int n, int[] sums, int sum) {
	List<List<Integer>> results = new LinkedList<>();

	if (n == 0) {
	    if (sum == theSum)
		results.add(new LinkedList<>());
	    return results;
	}

	Set<Integer> duplicates = new HashSet<>();

	for (int i = start; i < sums.length; i++) {
	    if (duplicates.contains(sums[i]))
		continue;
	    duplicates.add(sums[i]);
	    for (List<Integer> prefix : pickN(i + 1, n - 1, sums,
					      sum + sums[i])) {
		prefix.add(sums[i]);
		results.add(prefix);
	    }
	}
	return results;
    }

    private boolean testN(int[] picks, Map<Integer, Integer> picked, int start,
			  int k, int sum) {
	if (!testMap(picked, sum))
	    return false;
	if (k == 0)
	    return true;
	picked.merge(sum, 1, Integer::sum);

	Set<Integer> duplicates = new HashSet<>();

	for (int i = start; i < picks.length; i++) {
	    if (duplicates.contains(picks[i]))
		continue;
	    duplicates.add(picks[i]);
	    if (!testN(picks, picked, i + 1, k - 1, sum + picks[i]))
		return false;
	}
	return true;
    }

    private boolean testMap(Map<Integer, Integer> picked, int sum) {
	Integer repeats = map.get(sum);
	Integer actualRepeats = picked.get(sum);

	if (repeats == null)
	    return false;
	else if (actualRepeats == null)
	    return true;
	else if (repeats > actualRepeats)
	    return true;
	return false;
    }

    public static void main(String[] args) {
	/*
	 * 6
	 * [-755,-616,-178,-110,199,-765,-528,49,-1331,-1022,-377,-1249,-1321,-
	 * 466,-516,-666,10,-594,-227,-1132,189,-538,-477,-1259,0,-1181,-217,-
	 * 487,-289,-655,416,127,-1032,-903,-299,-872,-239,-188,-229,-905,-367,-
	 * 1142,-526,-606,-1558,-645,39,-944,-250,-676,-456,-100,-882,-260,-843,
	 * -833,-1548,-604,-893,-954,-915,117,426,-1171]
	 */
	System.out.println(Arrays.toString(new Solution().recoverArray(6,
								       new int[] { -755, -616, -178, -110, 199, -765, -528, 49, -1331,
										   -1022, -377, -1249, -1321, -466, -516, -666, 10, -594,
										   -227, -1132, 189, -538, -477, -1259, 0, -1181, -217,
										   -487, -289, -655, 416, 127, -1032, -903, -299, -872,
										   -239, -188, -229, -905, -367, -1142, -526, -606, -1558,
										   -645, 39, -944, -250, -676, -456, -100, -882, -260,
										   -843, -833, -1548, -604, -893, -954, -915, 117, 426,
										   -1171 })));
    }
}
