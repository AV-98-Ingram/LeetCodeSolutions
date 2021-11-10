class Solution {
    public int majorityElement(int[] nums) {
	/* a good enough solution: find the medium number
	   Arrays.sort(nums);
	   return nums[nums.length / 2];	
	*/
	// Boyer-Moore voting algorithm
	int counter = 0;
	int cand = 0;
	
	for (int n : nums) {
	    if (counter == 0) {
		cand = n;
		counter++;
	    } else {
		if (cand == n)
		    counter++;
		else
		    counter--;
	    }
	}
	return cand;
    }
}
