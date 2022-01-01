class Solution {
    public int[] findBuildings(int[] heights) {
	final int len = heights.length;
	int[] ret = new int[len];
	int max = 0;
	int j = len - 1;
	
	for (int i = len-1; i >= 0; i--) {
	    int bld = heights[i];
	    
	    if (bld > max)
		ret[j--] = i;
	    max = Math.max(max, bld);
	}
	return Arrays.copyOfRange(ret, j+1, len);
    }
}
