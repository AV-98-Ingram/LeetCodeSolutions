/* Idea:
   
   We can always divide the given array to a sequence of "region"s.  A
   region is an array segment a[i .. j] such that there exists a
   number k, i <= k <= j, such that a[i .. k-1] are all positives and
   rests are all negatives.

   For a region i, denoted reg(i), we denote the sum of all its
   positives pos(i) and sum of all its negatives neg(i).  

   Let span(j, i) be the sum of contiguous regions from region j to
   i. Speciallly, we define span(j, i) = 0 if j > i.

   The sum of the sub-array of maximum sum ends at region i must be

      span(j, i-1) + pos(i) s.t. FORALL int k; 0<=k<j span(k, i-1) < span(j, i-1).
      
   Visually, that is,

   ... reg(k), reg(j) ..., reg(i-1)

   pos(k) + neg(k) must be small enough such that span(0, k) < 0.
   Otherwise, why not adding span(0, k) to span(j, i-1)?  Note that if
   span(0, k) < 0 while reg(k) is not the negative impactor, we will
   have span(k, i-1) >= span(j, i-1), contradicting our assumption for
   span(j, i-1).  So reg(k) must be the negative impactor.
   

   Base on the observation above, we can do the following:

     Computes the sub-array of max sum that ends at region i, for each
     i starting from 0.  Maintaining the max one among those
     sub-arrays.

     To compute such sub-array ends at region i, we need the maximum
     span "span(j, i-1)".  Such maximum span is computed cumulatively
     along the scan:

     Base, step 0:  max_span := 0

     step i:  max_span := 0, if reg i is a "negative impactor";
                          max_span + pos(i) + neg(i), otherwise.

   Therefore, we only need to scan once to solve this problem.
 */

int check_region(int * nums, int numsSize, int i, int * pos, int * neg);

// requires 0 < numsSize;
int maxSubArray(int* nums, int numsSize){
  int max = nums[0];  
  int curr_sum = 0;
  
  for (int i = 0; i < numsSize;) {    
    if (nums[i] >= 0) {
      int region_pos, region_neg;
      
      i = check_region(nums, numsSize, i, &region_pos, &region_neg);
      curr_sum += region_pos;
      if (curr_sum > max)
	max = curr_sum;
      curr_sum += region_neg;
      if (curr_sum < 0) 
	curr_sum = 0;
    } else {      
      if (nums[i] > max)
	max = nums[i];   // in case of all negatives
      i++;
    }
  }
  return max;
}

int check_region(int * nums, int numsSize, int i, int * pos, int * neg) {
  *pos = 0;
  while(i < numsSize && nums[i] >= 0) 
    *pos += nums[i++];
  *neg = 0;
  while(i < numsSize && nums[i] < 0)
    *neg += nums[i++];
  return i;
}

#include "stdio.h"
#include "stdlib.h"

int main(int argc, char * argv[]) {
  int nums[argc-1];

  for (int i = 1; i < argc; i++)
    nums[i-1] = atoi(argv[i]);
  printf("%d\n", maxSubArray(nums, argc-1));
}
