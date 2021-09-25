class Solution {
    public int[][] generateMatrix(int n) {
        int roll = 0;
        int i = 1;
        int[][] a = new int[n][n];

	while (true) {
	    // top:
	    if (n - roll > roll)
		for (int k = roll; k < n-roll; k++)
		    a[roll][k] = i++;
	    else
		break;
	    // right:
	    if (n - roll > roll + 1)
		for (int k = roll + 1; k < n-roll; k++)
		    a[k][n-roll-1] = i++;
	    else
		break;
	    // bottom:
	    if (n - roll -1 > roll)
		for (int k = n - roll - 2; k >= roll; k--)
		    a[n-roll-1][k] = i++;
	    else
		break;
	    // left:
	    if (n - roll - 1 > roll + 1)
		for (int k = n - roll - 2; k >= roll + 1; k--)
		    a[k][roll] = i++;
	    else
		break;
	    roll++;
	}
	assert i == n * n + 1;
        return a;
    }
}
