class Solution {
    
    static int directs[][] = new int[][] {
	{-1, 0}, {0, 1}, {1, 0}, {0, -1}  // L: 0 -> 3, R: 3 -> 0	
    };
    
    public boolean isRobotBounded(String instructions) {
	int Ls = 0;
	int d = 0;
	int[] loc = {0, 0};
	
	for (char c : instructions.toCharArray()) {
	    if (c == 'L') {
		Ls++;
		d = (d+1) & (4-1);
	    } else if (c == 'R') {
		Ls--;
		d = ((d-1)+4) & (4-1);
	    } else {
		loc[0] += directs[d][0];
		loc[1] += directs[d][1];
	    }	    
	}	
	return  (loc[0] == 0 && loc[1] == 0) ||
	        (Ls & (4-1)) != 0; // Ls % 4 != 0
    }
}
