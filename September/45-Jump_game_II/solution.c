#include "stdlib.h"
#include "stdio.h"

/*
  This one is tricky in the way that it looks like DP is the best
  solution but in fact this problem is simpler:

    If the next step cannot reach the end, take the step such that the
    next 2 steps are max, denoted max_2.  Such choice will make you go
    as far as possible and won't miss any step as any cell from this
    position i to (i + max_2) is reachable in two steps.  
 */


int jump2(int *nums, int pos, int numsSize);

int jump(int* nums, int numsSize) {
  if (numsSize == 1)
    return 0;
  return jump2(nums, 0, numsSize);
}

int jump2(int *nums, int pos, int numsSize) {
  int step = nums[pos];
  int next_max = 0;
  int next_step;
  
  if (step + pos >= numsSize-1)
    return 1;
  for (int i = step; i >= 1; i--) {
    int next_max_cand = i + nums[pos + i];
    
    if (next_max_cand > next_max) {
      next_max = next_max_cand;
      next_step = i;
    }
  }
  return 1 + jump2(nums, pos + next_step, numsSize);
}

int main(int argc, char * argv[]) {
  int nums[argc-1];

  for (int i = 1; i < argc; i++)
    nums[i-1] = atoi(argv[i]);
  printf("%d\n", jump(nums, argc-1));
}
