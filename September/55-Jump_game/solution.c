char canJumpf(int* nums, int pos, int numsSize);

char canJump(int* nums, int numsSize) {
  return canJumpf(nums, 0, numsSize);
}

char canJumpf(int* nums, int pos, int numsSize) {   
  int jump = nums[pos];
  int max_next = jump, next_pos = pos;
  int i = 1;
  
  while (i <= jump) {
    if (pos + max_next >= numsSize-1)
      return 1;
    
    int step = nums[pos + i] + i;
    
    if (step > max_next) {
      max_next = step;
      next_pos = pos + i;
    }
    i++;
  }
  if (pos + max_next >= numsSize - 1)
    return 1;
  else if (next_pos <= pos)
    return 0;
  else
    return canJumpf(nums, next_pos, numsSize);  
}


#include "stdio.h"

int main() {
  int x[12] = {5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0};
  
  printf("%d\n", canJump(x, 12));
}
