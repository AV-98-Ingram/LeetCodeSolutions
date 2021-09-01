int findMin(int* nums, int numsSize){
  if (nums[0] >= nums[numsSize-1]) {
    // find first descending
    for (int i = 1; i < numsSize; i++) {
      if (nums[i] < nums[i-1])
	return nums[i];
    }
  }
  return nums[0];
}
