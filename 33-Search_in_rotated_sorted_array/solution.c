/* Thanks to the fact that the given array has distinct values, one
   can test if a segment of the given array is ascending or not.

   So the solution is similar to binary search.  To tell if a half
   contains the target, one can tell by testing the ascending half.

   Keep doing binary search, finally the target must fall in an
   ascending segment (such segment may have size 1) or non-exist.
 */

char is_non_ascending(int * nums, int size);
// pre-cond: range is ascending
char is_in_range(int * nums, int size, int target);
int * b_search(int* nums, int numsSize, int target);

#include "stdio.h"
#include "stdlib.h"

int search(int* nums, int numsSize, int target) {
  int * p = b_search(nums, numsSize, target);

  if (p == NULL)
    return -1;
  return p - nums;
}

int * b_search(int* nums, int numsSize, int target) {
  if (numsSize == 1)
    return nums[0] == target ? nums : 0;

  int mid = numsSize / 2;

  if (is_non_ascending(nums, mid))
    if (is_in_range(nums + mid, numsSize - mid, target))
      return b_search(nums + mid, numsSize - mid, target);
    else
      return b_search(nums, mid, target);
  else 
    if (is_in_range(nums, mid, target))
      return b_search(nums, mid, target);
    else
      return b_search(nums + mid, numsSize - mid, target);
}

char is_non_ascending(int * nums, int size) {
  return nums[0] > nums[size-1];
}

// pre-cond: range is ascending
char is_in_range(int * nums, int size, int target) {
  if (target >= nums[0])
    return target <= nums[size-1];
  return 0;
}

int main(int argc, char * argv[]) {
  int input[argc-2];
  int target;
  
  for (int i = 1; i < argc-1; i++)
    input[i-1] = atoi(argv[i]);
  target = atoi(argv[argc-1]);
  printf("%d\n", search(input, argc-2, target));
}
