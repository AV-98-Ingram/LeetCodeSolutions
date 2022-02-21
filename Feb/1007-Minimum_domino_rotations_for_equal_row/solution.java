class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
	final int len = tops.length;
	int  min = len+1;
	
	for (int i = 1; i <= 6; i++) {
	    int rot4top = 0, rot4bot = 0;

	    for (int j = 0; j < len; j++) {
		if (rot4top >= 0) {
		    if (tops[j] == i) { 
			// no-op  
		    } else if (bottoms[j] == i)
			rot4top++;
		    else
			rot4top = -1;
		}
		if (rot4bot >= 0) {
		    if (bottoms[j] == i) {
			// no-op
		    } else if (tops[j] == i)
			rot4bot++;
		    else
			rot4bot = -1;
		}
		if (rot4top < 0 && rot4bot < 0)
		    break;		
	    }
	    if (rot4top >= 0)
		min = Math.min(min, rot4top);
	    if (rot4bot >= 0)
		min = Math.min(min, rot4bot);
	}
	if (min == len + 1)
	    return -1;
	return min;
    }
}
