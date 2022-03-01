class Solution {
    public void moveZeroes(int[] nums) {
	final int len = nums.length;
	int l = 0, r = 0;

	// l points to the leftmost zero:
	while (l < len && nums[l] != 0) 
	    l++;
	// r points to the leftmost non-zero after l:
	r = l+1;
	while (r < len && nums[r] == 0) 
	    r++;
	while (r < len) { // eventually l reaches -1 & we r done
	    nums[l] = nums[r];
	    nums[r] = 0;
	    // invariant of l and r still holds
	    while (l < len && nums[l] != 0) 
		l++;
	    r = l + 1;
	    while (r < len && nums[r] == 0) 
		r++;	    
	}	
    }
}
