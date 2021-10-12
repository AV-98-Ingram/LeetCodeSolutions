class Solution {

    static Integer cache[][][] = new Integer[20][20][20];

    public int numTrees(int n) {
	int ret = 0;

	for (int i = 1; i <= n; i++)
	    ret += f(i, 1, n);
	return ret;
    }

    private int f(int root, int lb, int ub) {
	Integer cached = cache[root][lb][ub];

	if (cached != null)
	    return cached;

	int numLefts = root - lb;
	int numRights = ub - root;

	if (numLefts <= 0 && numRights <= 0)
	    return 1;
	if (numLefts > 1) {
	    numLefts = 0;
	    for (int i = lb; i < root; i++)
		numLefts += f(i, lb, root - 1);
	} else if (numLefts < 1)
	    numLefts = 1;
	if (numRights > 1) {
	    numRights = 0;
	    for (int i = root + 1; i <= ub; i++)
		numRights += f(i, root + 1, ub);
	} else if (numRights < 1)
	    numRights = 1;
	cache[root][lb][ub] = numLefts * numRights;
	return cache[root][lb][ub];
    }

    public static void main(String[] args) {
	new Solution().numTrees(3);
    }
}
