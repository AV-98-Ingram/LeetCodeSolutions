class RLEIterator {
    private int p = 0;
    private int[] ecd;
    public RLEIterator(int[] encoding) {
        ecd = encoding;
    }
    
    public int next(int n) {
        while (p < ecd.length - 1) {
	    if (ecd[p] >= n) {
		ecd[p] -= n;
		return ecd[p+1];
	    } else {
		n -= ecd[p];		
		p+=2;
	    }
	}
	return -1;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(encoding);
 * int param_1 = obj.next(n);
 */
