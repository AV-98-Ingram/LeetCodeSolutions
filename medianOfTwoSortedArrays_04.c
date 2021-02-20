#include "stdio.h"

int get_next(int* nums1, int * counter1, int nums1Size, 
	     int* nums2, int * counter2, int nums2Size) {
  int result;
  int c1 = *counter1, c2 = *counter2;
  
  if (c1 == nums1Size) {
    result = nums2[c2];
    *counter2 += 1;
  } else if (c2 == nums2Size) {
    result = nums1[c1];
    *counter1 += 1;
  } else if (nums1[c1] > nums2[c2]) {
    result = nums2[c2];
    *counter2 += 1;
  } else {
    result = nums1[c1];
    *counter1 += 1;
  }    
  return result;
}


double findMedianSortedArrays(int* nums1, int nums1Size, int* nums2, int nums2Size) {
  int medianPos = nums1Size + nums2Size;
  char takeTwo = 0;

  // speical handling for total count == 0 or 1:
  if (medianPos == 0)
    return 0.0;
  // general for total count > 1
  if (medianPos % 2 == 0) {
    takeTwo = 1;
    medianPos = medianPos / 2 - 1;
  } else
    medianPos = medianPos / 2;

  int counter_total = 0;
  int counter_1 = 0;
  int counter_2 = 0;
  double median;

  while (counter_total++ <= medianPos) {
    median = get_next(nums1, &counter_1, nums1Size, nums2, &counter_2, nums2Size);
    printf("median = %f\n", median);
  }
  if (takeTwo) {
    median += get_next(nums1, &counter_1, nums1Size, nums2, &counter_2, nums2Size);
    median /= 2;
  }
  return median;
}

#define N 2
#define M 1

int main() {
  int a[N] = {1, 2};
  int b[M] = {3};
  double x = findMedianSortedArrays(a, N, b, M);

  printf("%f\n", x);    
}
