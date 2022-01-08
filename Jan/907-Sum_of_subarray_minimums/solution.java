class Solution {
    public int sumSubarrayMins(int[] arr) {
	final int len = arr.length;
	int preBounds[] = new int[len];
	int postBounds[] = new int[len];

	preBounds[0] = -1;
	for (int i = 1; i < len; i++)
	    if (arr[i - 1] < arr[i])
		preBounds[i] = i - 1;
	    else {
		int j = i - 1;

		while (preBounds[j] >= 0 && arr[i] <= arr[preBounds[j]])
		    j = preBounds[j];
		preBounds[i] = preBounds[j];
	    }
	postBounds[len - 1] = len;
	for (int i = len - 2; i >= 0; i--)
	    if (arr[i] >= arr[i + 1])
		postBounds[i] = i + 1;
	    else {
		int j = i + 1;
		while (postBounds[j] < len && arr[i] < arr[postBounds[j]])
		    j = postBounds[j];
		postBounds[i] = postBounds[j];
	    }

	long result = 0;

	for (int i = 0; i < len; i++) {
	    long preBound = preBounds[i];
	    long postBound = postBounds[i];

	    result += (i - preBound) * arr[i];
	    result += (postBound - i - 1) * arr[i];
	    result += (i - preBound - 1) * (postBound - i - 1) * arr[i];
	}
	return (int) (result % (1000000007));
    }

    public static void main(String[] args) {
	new Solution().sumSubarrayMins(new int[] { 1, 8, 9, 4 });
    }
}
