public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {       
	char[] tmp = new char[4];
	int counter = 0;
	int nReads;
	
	do {
	    nReads = read4(tmp);
	    System.arraycopy(tmp, 0, buf, counter, nReads);
	    counter += nReads;	   
	} while (nReads == 4 && counter < n);
	return counter > n ? n : counter;
    }
}
