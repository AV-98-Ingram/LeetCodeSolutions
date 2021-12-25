class Solution {
    /*
      This is the best solution given by LC.  It is based on the
      theorem:

        Maintaining a subsequence sub during the scan in a way that if
	the next number n is greater than the tail of sub, append it
	to the sub; otherwise, replace the first element in sub that
	is no less than n with n.  It is an invariant that len(sub) is
	the length of the longest increasing subsequence of the
	scanned prefix at each step.

      Proof sketch:
        Base: 
	   Immediately after scanning the first number, len(sub) is 1 thus the invariant holds.
	Induction:
	   Immediately after scanning i numbers, assume len(sub) is the length of the LIS.
	   1) Then if the (i+1)-th number n > tail(sub), we have
	   len(append(sub, n)) = len(sub) + 1, the invariant still
	   holds.

           2) For the case that n <= tail(sub), suppose there is
           another increasing subsequence sub' of the prefix to which
           n can append and len(sub') >= len(sub).  According to the
           invariant, we could restrict the constraint to len(sub') =
           len(sub).  Since n can be appended to sub', we have
           tail(sub') < tail(sub).  Then we can also append tail(sub)
           to tail(sub') which results in a new increasing subsequence
           sub'' = append(sub', tail(sub).  Now sub'' is also a
           increasing subsequence of the scanned prefix and len(sub'')
           = len(sub') + 1 > len(sub), it contradicts the invariant.
 
           Thus, there does not exist such sub' in this case.
           len(sub) is still the length of LIS after the replacement.
           Invariant holds.	   
     */    
    public int lengthOfLIS(int[] nums) {
	final int len = nums.length;
	TreeSet<Integer> sub = new TreeSet<>();	    
	
	sub.add(nums[0]);
	for (int i = 1; i < len; i++) {
	    int last = sub.last();
	    if (nums[i] > last) {
		sub.add(nums[i]);
	    } else {
		int ceiling = sub.ceiling(nums[i]);
		
		if (ceiling != nums[i]) {
		    sub.remove(ceiling);
		    sub.add(nums[i]);
		}
	    }
	}
	return sub.size();
    }
}
