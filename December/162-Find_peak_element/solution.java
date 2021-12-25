class Solution {
    /** A key pre-condition of using binary search in this problem is
     * that any two adjacent elements are different. So that there
     * must exist a peak element, considering the two ends to be
     * negative infinity.  
     * 
     * Thus, we can use binary search to only follow any ascending
     * side, i.e., (the side to the left where nums[mid-1] > nums[mid]
     * or the side to the right where nums[mid+1] > nums[mid]).
     * Because the ascending side would either keeps ascending to the
     * end, where the end is a peak, or becomes descending somewhere
     * thus a peak appears.
     */
    public int findPeakElement(int[] nums) {
	final int len = nums.length;

	return bSearch(0, len, nums);
    }
    
    private int bSearch(int start, int end, int[] nums) {
	int mid = (end - start)/2 + start;

	if (isPeak(nums, mid))
	    return mid;
	if (mid < nums.length - 1 && nums[mid+1] > nums[mid])
	    return bSearch(mid+1, end, nums);
	else
	    return bSearch(start, mid, nums);
    }
    
    private boolean isPeak(int[] nums, int idx) {
	final int len = nums.length;
	
	if (idx == 0 && idx == len-1)
	    return true;	
	if (idx == 0)
	    return nums[0] > nums[1];		
	if (idx == len-1)
	    return nums[len-1] > nums[len-2];
	return nums[idx-1] < nums[idx] && nums[idx] > nums[idx+1];
    }
}
