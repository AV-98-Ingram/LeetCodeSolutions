/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
/* simple binary search, nothing special */
int* b_search(int* nums, int numsSize, int target, int ** last) {
  if (numsSize == 1) {
    if (nums[0] == target) {
      *last = nums;
      return nums;
    }
    *last = 0;
    return 0;
  }

  int mid = numsSize / 2;

  if (nums[mid] == target) {
    // search both half:
    int * start = b_search(nums, mid, target, last);

    if (start == 0)
      start = nums + mid;
    b_search(nums + mid, numsSize - mid, target, last);
    return start;
  } else if (nums[mid] > target) {
    return b_search(nums, mid, target, last);
  } else 
    return b_search(nums + mid, numsSize - mid, target, last);
}

int* searchRange(int* nums, int numsSize, int target, int* returnSize) {
  int * last, * start;
  int * ret = (int*)malloc(sizeof(int) * 2);
  
  *returnSize = 2;
  // special case:
  if (numsSize <= 0) {
    ret[0] = -1; ret[1] = -1;
    return ret;
  }
  // general case:
  start = b_search(nums, numsSize, target, &last);
  if (start == 0) {
    ret[0] = -1; ret[1] = -1;   
    return ret;
  }
  ret[0] = start - nums;
  ret[1] = last - nums;
  return ret;
}

#include "stdlib.h"
#include "stdio.h"

int main(int argc, char * argv[]) {
  int input[argc-2];
  int target;
  
  for (int i = 1; i < argc-1; i++)
    input[i-1] = atoi(argv[i]);
  target = atoi(argv[argc-1]);

  int * ret = searchRange(input, argc-2, target, &target);
  
  printf("%d %d\n", ret[0], ret[1]);
}

