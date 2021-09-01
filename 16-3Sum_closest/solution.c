/*  A nearly brutal force solution.

    Sort the nums array.
    
    foreach (i : 0 .. len(nums))
      foreach (j : i+1 .. len(nums))
        binary_search(nums,  target - nums[i] - nums[j]);

    Time: O(n*log(n) + n^2 * log(n))

 */
#include "stdio.h"
#include "stdlib.h"

#define T int
#define SWAP_T(x, y) {T tmp = (x); (x) = (y); (y) = tmp;}

int partition(T * a, int start, int end, int pivot) {
  int left = pivot, right = pivot; 
  
  while (left >= start || right < end) {
    if (left < start) {
      if (a[right] < a[pivot]) {
	if (pivot + 1 < right)
	  SWAP_T(a[pivot], a[pivot+1]);	  
	SWAP_T(a[pivot], a[right]);
	pivot++;
      }
      right++;
    } else if (right >= end) {
      if (a[left] > a[pivot]) {
	if (pivot - 1 > left)
	  SWAP_T(a[pivot], a[pivot-1]);
	SWAP_T(a[pivot], a[left]);
	pivot--;
      }
      left--;
    } else if (a[left] > a[pivot] && a[right] < a[pivot]) {      
      // swap left and right:
      SWAP_T(a[left], a[right]);
      left--; right++;
    } else if (a[left] > a[pivot]) 
      right++;
    else if (a[right] < a[pivot]) 
      left--;
    else {
      left--; right++;
    }
  }
  return pivot;
}


void quick_sort(T * a, int start, int end) {
  if (start + 1 >= end)
    return;
  
  int pivot = (end-start)/2 + start;
  
  pivot = partition(a, start, end, pivot);
  quick_sort(a, start, pivot);
  quick_sort(a, pivot, end);  
}

#define ABS(x) ((x) >= 0?(x):-(x))

// start inclusive; end exclusive
int binary(int* a, int start, int end, int val) {
  if (start + 1 >= end) 
    return a[start];
  
  int mid = (end - start)/2 + start;
  
  if (a[mid] > val) {
    int ret = binary(a, start, mid, val);

    return ABS(ret-val) > ABS(a[mid]-val) ? a[mid] : ret;
  }
  else
    return binary(a, mid, end, val);
}

int threeSumClosest(int* nums, int numsSize, int target) {
  quick_sort(nums, 0, numsSize);

  int closet = nums[0] + nums[1] + nums[2];
  
  for (int i = 0; i < numsSize-2; i++)
    for (int j = i + 1; j < numsSize-1; j++) {
      int val = target - nums[i] - nums[j];
      int close_val = binary(nums, j+1, numsSize, val);

      if (close_val == val)
	return target;
      if (ABS(close_val - val) < ABS(closet - target))
	closet = nums[i] + nums[j] + close_val;
    }
  return closet;
}

int main(int argc, char * argv[]) {
  int in[argc-2];
  int target = atoi(argv[argc-1]);
    
  for (int i = 0; i < argc - 2; i++)
    in[i] = atoi(argv[i+1]);
  printf("%d\n", threeSumClosest(in, argc-2, target));
}
