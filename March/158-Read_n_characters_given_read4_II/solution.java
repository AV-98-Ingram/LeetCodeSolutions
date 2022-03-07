public class Solution extends Reader4 {
    
    static LinkedList<Character> lst = new LinkedList<>();

    public Solution() {
	lst.clear();
    }
    
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
	int p = 0;
	int rm = Math.min(lst.size(), n);

	for (int i = 0; i < rm; i++)
	    buf[p++] = lst.removeFirst();
	
	int k = (int) Math.ceil((double)(n-rm) / 4.0);
	
	for (int i = 0; i < k; i++) {
	    char[] c4 = new char[4];
	    int m = read4(c4);
	    int actual_m = Math.min(n-p, m);

	    if (actual_m > 0) {
		System.arraycopy(c4, 0, buf, p, actual_m);
		p += actual_m;
	    }
	    if (actual_m < m) {
		for (int j = actual_m; j < m; j++)
		    lst.add(c4[j]);
	    }
	    if (m < 4)
		break;	    
	}
	return p;
    }
}
