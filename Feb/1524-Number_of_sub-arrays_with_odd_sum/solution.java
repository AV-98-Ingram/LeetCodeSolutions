class Solution {
    public int numOfSubarrays(int[] arr) {
        long numOdds = 0;
	final int len = arr.length;
	int numOddsAtPrev = 0; // num "good" subarrays ending at previous index
	
	for (int i = 0; i < len; i++) {
	    if ((arr[i] & 1) == 0) {
		// even:
		int oddHere = numOddsAtPrev;
		
		numOdds += oddHere;
		numOddsAtPrev = oddHere;
	    } else {
		// odd:
		int numEvens = total(i-1) - numOddsAtPrev;
		int oddHere = numEvens + 1;
		
		numOdds += oddHere;
		numOddsAtPrev = oddHere;
	    }
	    numOdds = numOdds % 1000000007;
	}
	return (int)numOdds;
    }
    
    private int total(int n) { // total number of subarrays ending at index n
	return n + 1;
    }
}
