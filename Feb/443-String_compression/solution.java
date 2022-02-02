class Solution {
    public int compress(char[] chars) {
	int nxtOri = 0, nxtRet = 0;
	final int len = chars.length;
	
	while (nxtOri < len) {
	    // always start a new group
	    char grpChar = chars[nxtOri++];
	    int grpLen = 1;

	    while (nxtOri < len && grpChar == chars[nxtOri]) {
		grpLen++;
		nxtOri++;
	    }
	    // write group:
	    chars[nxtRet++] = grpChar;
	    if (grpLen > 1)
		for (int di : parseNum(grpLen))
		    chars[nxtRet++] = (char)(di + '0');	    
	}
	return nxtRet;
    }

    //requires n > 1
    private LinkedList<Integer> parseNum(int n) {
	LinkedList<Integer> result = new LinkedList<>();
	
	while (n > 0) {
	    int di = n % 10;
	    
	    result.addFirst(di);
	    n = n / 10;
	}
	return result;
    }
}
