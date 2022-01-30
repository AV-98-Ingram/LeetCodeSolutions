class Solution {
    /*
      Basically maintain a max window of k odd numbers in it.  Suppose
      there are n contigous even numbers on the left end and there are
      m even numbers on the right end.  The number of nice sub-arrays
      in this max window is (n+1) * (m+1).
      
      Then shift to the next window by fixing the left end to be at
      the right neighbor of the 1st odd number of current window and
      move the right end to find the next max window.  Until right end
      reaches the end of the given array.

     */
    public int numberOfSubarrays(int[] nums, int k) {
	int left = 0, right = 0; // left inclusive, right exclusive
	int numOdds = 0;
	int lastOdd = -1;
	int result = 0;

	do {
	    assert numOdds < k;
	    // shift the right end to form a max nice-array window:
	    while (numOdds <= k && right < nums.length) {
		if ((nums[right] & 1) == 1) {
		    numOdds++;
		    if (numOdds <= k)
			lastOdd = right;
		    else
			break;
		}
		right++;
	    }
	    // loop terminated with either of the conditions below holds
	    // 1. "numOdds == k+1 && right points-to (k+1)-th odd", OR
	    // 2. "right == nums.length"
	    if (numOdds < k)
		break; // terminate

	    // shift the left end to shrink to a new window of (k-1) odd numbers and compute
	    // nice sub-arrays in the old (before shrinking) windown:
	    int numEvensOnLeft = 0;

	    while ((nums[left] & 1) == 0) {
		left++;
		numEvensOnLeft++;
	    }
	    left++;
	    numOdds = k - 1;
	    result += (numEvensOnLeft + 1) * (right - lastOdd);
	} while (right < nums.length);
	return result;
    }
    
}
