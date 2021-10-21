/*
   Sort the intervals by their upper bounds.  Then for an interval
   pairs[i], it is impossible for pairs[i+1] to lead a longer chain
   than the one leaded by pairs[i].  Thus, no need to do backtrack
   tryouts.  It only needs one scan.
   
 */

class Solution {
    public int findLongestChain(int[][] pairs) {
	Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));
	
	int[] tail;
	int len = 0;
	
	for (int i = 0; i < pairs.length;) {
	    int j;
	    
	    tail = pairs[i];
	    len++;
	    for (j = i+1; j < pairs.length; j++)
		if (tail[1] < pairs[j][0])
		    break;		    	
	    i = j;
	}
	return len;
    }
}
