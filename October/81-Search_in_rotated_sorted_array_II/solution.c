char b_search(int *nums, int start, int end, int target);

char search(int* nums, int numsSize, int target) {
  int end = numsSize - 1;
  int start = 0;
  int end_num = nums[end];
  
  if (end_num == target)
    return 1;
  if (end_num == nums[start]) {
    while (end_num == nums[end] && end > start)
      end--;
    while (end_num == nums[start] && start < end)
      start++;
  }
  return b_search(nums, start, end, target);
}

char b_search(int *nums, int start, int end, int target) {
  if (start == end)
    return nums[start] == target;
  
  int mid = (end - start) / 2 + start;
  int mid_v = nums[mid];
  
  if (mid_v == target)
    return 1;
  if (mid_v > target) {
    if (nums[start] > mid_v)
      return b_search(nums, start, mid, target);    
    if (!b_search(nums, start, mid, target))
      return b_search(nums, mid+1, end, target);
    return 1;
  } else {
    if (nums[start] > mid_v) {
      if (!b_search(nums, mid+1, end, target))
	return b_search(nums, start, mid, target);
      return 1;
    } else
      return b_search(nums, mid + 1, end, target);
  }
}

#include "stdio.h"

int main() {
  int a[4] = {0,1,1,0};
  printf("%d\n", search(a, 4, 1));
}


