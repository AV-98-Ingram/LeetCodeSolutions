import java.util.HashMap;
import java.util.Map;

class Solution {

    class Triple {
	int i, j, k;

	Triple(int i, int j, int k) {
	    this.i = i;
	    this.j = j;
	    this.k = k;
	}

	public int hashCode() {
	    return i * 2001 + j * 101 + k;
	}

	public boolean equals(Object o) {
	    if (o instanceof Triple) {
		Triple other = (Solution.Triple) o;

		return other.i == i && other.j == j && other.k == k;
	    }
	    return false;
	}
    }

    Map<Triple, Integer> cache = new HashMap<>();

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
	return backtrack(houses, cost, m, n, target, 0, 0, 0);
    }

    private int backtrack(int[] houses, int[][] cost, int m, int n, int target, int groups, int pos, int currCost) {
	if (pos == m)
	    if (groups == target)
		return currCost;
	    else
		return -1;
	if (target < groups)
	    return -1;

	Integer cached = cache.get(new Triple(pos, pos == 0 ? 0 : houses[pos - 1], groups));

	if (cached != null)
	    return cached >= 0 ? cached + currCost : -1;

	int min = -1;

	if (houses[pos] > 0) {
	    int nxtGroups = groups;

	    if (pos == 0 || houses[pos] != houses[pos - 1])
		nxtGroups++;
	    min = backtrack(houses, cost, m, n, target, nxtGroups, pos + 1, currCost);
	    cache.put(new Triple(pos, pos == 0 ? 0 : houses[pos - 1], groups), min - currCost);
	    return min;
	}
	for (int i = 0; i < n; i++) {
	    int theCost;

	    houses[pos] = i + 1;
	    if (pos == 0 || houses[pos] != houses[pos - 1])
		theCost = backtrack(houses, cost, m, n, target, groups + 1, pos + 1, currCost + cost[pos][i]);
	    else
		theCost = backtrack(houses, cost, m, n, target, groups, pos + 1, currCost + cost[pos][i]);
	    houses[pos] = 0;
	    if (min == -1)
		min = theCost;
	    else if (theCost >= 0 && min > theCost)
		min = theCost;
	}
	cache.put(new Triple(pos, pos == 0 ? 0 : houses[pos - 1], groups), min - currCost);
	return min;
    }

    public static void main(String args[]) {
	new Solution().minCost(new int[] { 0, 0, 0, 0, 0 },
			       new int[][] { { 1, 10 }, { 10, 1 }, { 10, 1 }, { 1, 10 }, { 5, 1 } }, 5, 2, 3);
    }
}
