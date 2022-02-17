class Solution {
    public int findComplement(int num) {
	int ret = 0;
	boolean start = false;

	// flip bit by bit
	for (int i = 31; i >= 0; i--) {
	    int bit = num & (1 << i);

	    if (!start)
		if (bit == 0)
		    continue;
		else
		    start = true;
	    // started:
	    ret = ret | (bit ^ (1 << i));
	}
	return ret;
    }
}
