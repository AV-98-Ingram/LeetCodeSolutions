class Solution {
    /*
     * Window sliding:
     * 
     * To find such a window [l, r] that 
     * 1) size([l, r]) == total-number-of-ones; and 
     * 2) the number of '1's in [l, r] is max
     * 
     */
    public int minSwaps(int[] data) {
	int l = 0, r = 0; // l inclusive, r exclusive
	int max = 0;
	int ones = 0;
	int n = 0;

	for (int d : data)
	    if (d == 1)
		n++;
	if (n == 0 || n == 1)
	    return 0;
	do {
	    // move l to next 1
	    while (data[l] != 1)
		l++;
	    if (l >= r) // avoid to count counted data twice
		ones++;
	    // move r to r - l == n
	    while (r - l < n && r < data.length) {
		if (data[r] == 1 && r > l) // avoid to count data[l] twice
		    ones++;
		r++;
	    }
	    max = Math.max(max, ones);
	    ones--;
	    l++;
	} while (r < data.length);
	return n - max;
    }

    public static void main(String[] args) {
	new Solution().minSwaps(new int[] { 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1 });

    }
}
