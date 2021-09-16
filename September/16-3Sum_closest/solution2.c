/*  The "two-pointers" solution.

    Fix a number a[i], maintain two pointers left and right, which are
    initially, i+1 and n-1.  If a[left] + a[right] + a[i] < target,
    left++; if a[left] + a[right] + a[i] > target, right++.

    In addition, maintaining a closest value.

    Time: O(n^2)

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


int threeSumClosest(int* nums, int numsSize, int target) {
  quick_sort(nums, 0, numsSize);

  int closest = nums[0] + nums[1] + nums[2];
  
  for (int i = 0; i < numsSize-2; i++) {
    int left = i + 1;
    int right = numsSize - 1;

    while (left < right) {
      int val = nums[i] + nums[left] + nums[right];
      
      if (val == target)
	return val;
      if (ABS(val-target) < ABS(closest-target))
	closest = val;
      if (val > target)
	right--;
      else
	left++;
    }
  }
  return closest;
}

int main(int argc, char * argv[]) {
  int in[argc-2];
  int target = atoi(argv[argc-1]);
    
  for (int i = 0; i < argc - 2; i++)
    in[i] = atoi(argv[i+1]);
  printf("%d\n", threeSumClosest(in, argc-2, target));
}
