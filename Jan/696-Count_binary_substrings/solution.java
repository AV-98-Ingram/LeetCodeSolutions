class Solution {
    public int countBinarySubstrings(String s) {
	final int len = s.length();
	int result = 0;
	
	for (int i = 0; i < len; i++) {
	    boolean prevIsZero = s.charAt(i) == '0';
	    byte change = 0;
	    int balance = prevIsZero ? -1 : 1;
	    
	    for (int j = i+1; j < len; j++) {
		boolean isZero = s.charAt(j) == '0';
		
		balance += (isZero ? -1 : 1);
		if (prevIsZero != isZero) {
		    prevIsZero = isZero;
		    change++;
		}
		if (change > 1) {
		    break;
		}
		if (balance == 0) {
		    result++;
		    break;
		}
	    }
	}
	return result;
    }
}
