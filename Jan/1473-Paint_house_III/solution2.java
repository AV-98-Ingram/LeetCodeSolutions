import java.util.Arrays;
import java.util.TreeSet;

class Solution {

    class Pair {
	TreeSet<int[]> pair;

	Pair() {
	    pair = new TreeSet<>((a, b) -> (Integer.compare(a[0], b[0]) == 0 ? Integer.compare(a[1], b[1])
					    : Integer.compare(a[0], b[0])));
	}

	void add(int[] costColor) {
	    if (pair.size() < 2)
		pair.add(costColor);
	    else {
		int[] last = pair.pollLast();

		if (last[0] > costColor[0])
		    pair.add(costColor);
		else
		    pair.add(last);
	    }
	}

	int get(int conflictColor) {
	    assert !pair.isEmpty();
	    if (pair.first()[1] != conflictColor)
		return pair.first()[1];
	    if (pair.last()[1] != conflictColor)
		return pair.last()[1];
	    return 0;
	}
    }

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
	// dp[i][t][j]: the min cost paining houses[0 .. i] into t groups and house[i]
	// is painted color j+1
	int dp[][][] = new int[m][target + 1][n];
	// colors of min and second min cost in dp[i][t][...]
	Pair mins[][] = new Pair[m][target + 1];

	for (int i = 0; i < n; i++) {
	    int c;

	    if (houses[0] == 0)
		c = cost[0][i];
	    else if (houses[0] != i + 1)
		c = -1;
	    else
		c = 0;
	    dp[0][1][i] = c;
	    dp[0][0][i] = -1; // 0 group is impossible
	    updateMins(0, 1, i + 1, c, mins); // update min and smin
	}

	for (int i = 1; i < m; i++) {
	    final int t_ub = Math.min(target, i + 1);

	    Arrays.fill(dp[i][0], -1);
	    for (int t = 1; t <= t_ub; t++) {
		if (houses[i] > 0) {
		    int fixedColor = houses[i];
		    int leftColor = selectMin(mins, i - 1, t - 1, fixedColor);
		    int cost1 = leftColor > 0 ? dp[i - 1][t - 1][leftColor - 1] : -1;
		    int cost2 = t <= i ? dp[i - 1][t][fixedColor - 1] : -1;

		    Arrays.fill(dp[i][t], -1);
		    dp[i][t][fixedColor - 1] = minCost(cost1, cost2);
		    updateMins(i, t, fixedColor, dp[i][t][fixedColor - 1], mins);
		} else {
		    for (int j = 0; j < n; j++) {
			int leftColor = selectMin(mins, i - 1, t - 1, j + 1);
			int cost1 = (leftColor > 0) ? dp[i - 1][t - 1][leftColor - 1] + cost[i][j] : -1;
			int cost2 = (t <= i && dp[i - 1][t][j] >= 0) ? dp[i - 1][t][j] + cost[i][j] : -1;

			dp[i][t][j] = minCost(cost1, cost2);
			updateMins(i, t, j + 1, dp[i][t][j], mins);
		    }
		}
	    }
	}

	int minColor = selectMin(mins, m - 1, target, 0);

	return minColor > 0 ? dp[m - 1][target][minColor - 1] : -1;
    }

    private void updateMins(int house, int group, int color, int cost, Pair[][] mins) {
	if (cost < 0)
	    return;
	if (mins[house][group] == null)
	    mins[house][group] = new Pair();
	mins[house][group].add(new int[] { cost, color });
    }

    private int selectMin(Pair[][] mins, int house, int group, int conflictColor) {
	if (mins[house][group] == null)
	    return 0;
	return mins[house][group].get(conflictColor);
    }

    private int minCost(int cost1, int cost2) {
	if (cost1 < 0)
	    return cost2;
	if (cost2 < 0)
	    return cost1;
	return Math.min(cost1, cost2);
    }
}
