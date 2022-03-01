class Solution {
    public int maxPower(String s) {
	char prev = 0;
	int max = 0;
	int streak = 0;
	
	for (char c : s.toCharArray()) {
	    if (c != prev) {
		max = Math.max(max, streak);
		streak = 1;
	    } else {
		streak++;
	    }
	    prev = c;	    	    
	}
	max = Math.max(max, streak);	
	return max;
    }
}
