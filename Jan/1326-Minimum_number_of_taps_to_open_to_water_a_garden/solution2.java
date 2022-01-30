class Solution {
    // The Jump Game solution
    public int minTaps(int n, int[] ranges) {
	int jumps[] = new int[n + 1];

	for (int i = 0; i <= n; i++) {
	    int start = Math.max(0, i - ranges[i]);
	    int end = Math.min(n, i + ranges[i]);

	    jumps[start] = Math.max(jumps[start], end);
	}

	int jump = jumps[0];
	int nextJump = jump;
	int ntaps = 0;
	int i;

	for (i = 1; i <= jump; i++) {
	    if (jumps[i] > nextJump) {
		nextJump = jumps[i];
	    }
	    if (i == jump) {
		jump = nextJump;
		ntaps++;
	    }
	}
	if (i > n)
	    return ntaps;
	return -1;
    }

    public static void main(String[] args) {
	new Solution().minTaps(5, new int[] { 3, 4, 1, 1, 0, 0 });
    }
}
