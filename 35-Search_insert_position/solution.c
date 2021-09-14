int * b_search(int* nums, int numsSize, int target) {
  if (numsSize == 1) {
    if (nums[0] < target)
      return nums + 1;
    else
      return nums;
  }

  int mid = numsSize / 2;

  if (nums[mid] >= target)
    return b_search(nums, mid, target);
  else
    return b_search(nums + mid, numsSize - mid, target);
}

int searchInsert(int* nums, int numsSize, int target){
  int * p = b_search(nums, numsSize, target);

  return p - nums;
}
