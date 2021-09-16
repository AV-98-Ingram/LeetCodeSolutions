#include "stdio.h"

int removeDuplicates(int* nums, int numsSize){
  if (numsSize <= 0)
    return 0;
  
  int move_ahead = 0;
  int prev = nums[0];
  
  for (int i = 1; i < numsSize; i++) {
    if (nums[i] == prev) {
      move_ahead++;
      continue;
    }
    prev = nums[i];
    nums[i - move_ahead] = nums[i];
  }
  return numsSize - move_ahead;
}

int main() {
  int a[10] = {1,2,2,3,3,5,5,8,9,9};

  int k = removeDuplicates(a, 10);

  for (int i = 0; i < k;)
    printf("%3d", a[i++]);
}
