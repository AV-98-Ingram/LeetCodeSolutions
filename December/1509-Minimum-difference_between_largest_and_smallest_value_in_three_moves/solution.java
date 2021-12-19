class Solution {
    /*
      Window moving:

      First to sort the array.  Then try to change numbers at two ends
      to the numbers in middle so that they become more close.  Then
      we just shift the window and find the minimum solution:

      start from window [0 .. k] meaning that we change numbers all at the left end; (note k = 3 in this problem)
      then [0 .. k-1, n-1] menaing that we change k-1 numbers at left end and change one number at right;
      then [0 .. k-2, n-2 .. n-1],
      then ...                  
     */
    public int minDifference(int[] nums) {
	if (nums.length <= 4)
	    return 0;
	Arrays.sort(nums);
	
	// window moving:
	final int len = nums.length;
	int l = 3, r = len-1;
	int min = nums[r] - nums[l];
	
	for (int i = 1; i <= 3; i++) {
	    l--; r--;	    
	    min = Math.min(min, nums[r] - nums[l]);
	}
	return min;
    }
}

