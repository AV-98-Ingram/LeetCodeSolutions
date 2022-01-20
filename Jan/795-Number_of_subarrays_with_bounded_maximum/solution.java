class Solution {
    // O(n^2) too slow
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
	int result = 0;
	int lb = -1, rb = 1; // exclusive
	
	for (int i = 0; i < nums.length; i++) {
	    if (nums[i] < left || nums[i] > right)
		continue;

	    // optimization here for the case nums[i-1] == nums[i]
	    if (i > 0 && nums[i-1] == nums[i]) {
		if (rb == i)
		    rb = i+1;
	    } else {
		lb = i-1; 
		rb = i+1; 
	    }
	    while (lb >= 0 && nums[lb] <= nums[i])
		lb--;
	    while (rb < nums.length && nums[rb] < nums[i])
		rb++;

	    int lWing = (i - lb - 1) ;
	    int rWing = (rb - i - 1) ;

	    result += lWing + rWing + (lWing * rWing) + 1;
	}
	return result;
    }

    public static void main(String[] args) {
	new Solution().numSubarrayBoundedMax(new int[] { 73, 55, 36, 5, 55, 14, 9, 7, 72, 52 }, 32, 69);
    }
}
