class Solution {
    public int minCost(String colors, int[] neededTime) {
	int l = 0;
	int r = 0;
	int max = 0;
	int maxIdx = 0;
	int cost = 0;
	char[] str = colors.toCharArray();

	while (r < str.length) {
	    if (str[l] == str[r]) {
		if (neededTime[r] > max) {
		    max = neededTime[r];
		    maxIdx = r;
		}
		r++;
	    } else {
		int nrepeats = r - l;

		if (nrepeats > 1)
		    for (int j = l; j < r; j++)
			if (j != maxIdx)
			    cost += neededTime[j];
		l = r;
		max = 0;
	    }
	}

	int nrepeats = r - l;

	if (nrepeats > 1)
	    for (int j = l; j < r; j++)
		if (j != maxIdx)
		    cost += neededTime[j];
	return cost;
    }

    public static void main(String[] args) {
	new Solution().minCost("aabaa", new int[] { 1, 2, 3, 4, 1 });
    }
}
