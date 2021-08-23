/*  Let a_0, a_1, ..., a_i, ... be the first array A of length n.
    Let b_0, b_1, ..., b_j, ... be the second array B of length m.

    If we merge the two arrays into a new sorted array C, C is formed by
    interspersing elements in A with the elements in B:

      c_0, c_1, ..., c_i, ..., c_j, ..., c_{n + m -1}, where 0 <= i < j < n + m
   
    Note that for any c_i and c_j, if they are from the same original
    array, their original order is preserved.

    We use m(A, a_i) to denote that a_i is the element in A that is
    involved in computing the median of A. That is, a_i == a_{n/2} if
    n is odd or n/2-1 <= i <= n/2 if n is even.

    Now let a' and b' be the medians of A and B, respectively.
    Without loss of generality, we assume a' < b'.  Note that if a' ==
    b', we are done.

    Let A_ be the largest prefix of A that EXCLUDES elements s.t.  m(A, a),
    i.e.,
       A_ = {a_0, a_1, ..., a_{n/2 - 1}} if n is odd.
       A_ = {a_0, a_1, ..., a_{n/2 - 2}} if n is even.

    Let B^ be the largest suffix of B that EXCLUDES elements s.t. m(B,
    b), i.e.,
       B^ = {b_{m/2 + 1}, ..., b_{m-1}}.   
    
    Theorem: the elements involved in computing the median of A and B is
    NOT in min(A_, B^).

    Proof:

      Lemma 1: the element c such that m(C, c) is not in A_.

      Proof: Let a' and b' be the medians of A and B, respectively.
             For any element a in the prefix A_, there are at least
             (n/2+1) + (m/2+1) elements in C that are greater than or
             equal to a.  Because         
               a <= a' <= A^   and   a <= a' < b' <= B^  and
               size(A^) = n / 2   and size(B^) = m / 2.

             The element c in C that m(C, c) must be the ((n+m)/2)-th
             element if n+m is odd or is in the range [(n+m)/2-1,
             (n+m)/2] if n+m is even.  In either case, there will be
             NO MORE THAN n/2 + m/2 elements that are greaer than or
             equal to c.  Thus, a cannot be c.

	     To show why "NO MORE THAN n/2 + m/2 elements", one can
	     break down the cases for n+m, i.e.,
	     1. n+m is odd, then one is odd and one is even;
             2. n+m is even and both n and m are odd, or
             3. n+m is even and both n and m are even.

      Lemma 2: the element c such that m(C, c) is not in B^.
      Proof: similar to the proof of Lemma 1.

    Theorem 1 is simply the consequence of Lemma 1 and 2.


    With Theorem 1, we design an algorithm to get rid of min(A_, B^)
    elements from the head of A and the end of B.  By iteraively doing
    so, every iteration the smaller array reduces by (half - 1).  The
    algorithm iterates until the smaller array reduces to at most 2
    elements.  Then we spent a constant time to find out the median of
    a sorted array and two (or one, or zero) separate elements.
 */

#include "stdio.h"
#include "string.h"

#define min(x, y)  ((x)>(y)?(y):(x))
#define max(x, y)  ((x)>(y)?(x):(y))

double median(int *arr, int len) {
  if (len == 0)
    return 0.0;
  if (len % 2 == 0) 
    return ((double)(arr[len/2] + arr[len/2-1])) / 2.0;
  else
    return arr[len/2];
}

// simply merges the two arrays, sorts them and finds out the median.
// This function will be called when nums1Size + nums2Size <= 6, so
// the sorting is considered a constant time operation.
double median_by_sort(int *nums1, int nums1Size, int* nums2, int nums2Size) {
  int len = nums1Size + nums2Size;
  if (len == 0)
    return 0.0;
  int a[len];
  memcpy(a, nums1, nums1Size * sizeof(int));
  memcpy(a + nums1Size, nums2, nums2Size * sizeof(int));
  for (int i = 0; i < len-1; i++)
    for (int j = 0; j < len-1; j++)
      if (a[j] > a[j+1]) {
	int tmp = a[j+1];
	a[j+1] = a[j];
	a[j] = tmp;
      }
  return median(a, len);
}

/*  Given a sorted array a[n] and a separated integer, finds out the
    median of them.  This function requires n >= 3.  Constant time
    complexity.
*/
double median_by_interpolate_one(int num, int* nums2, int nums2Size) {
  if (nums2Size % 2 == 0) {
    if (num <= nums2[nums2Size/2-1])
      return nums2[nums2Size/2-1];
    if (num >= nums2[nums2Size/2])
      return nums2[nums2Size/2];
    return num;
  } else {
    if (num <= nums2[nums2Size/2-1])
      return (double)(nums2[nums2Size/2-1] + nums2[nums2Size/2]) / 2.0;
    if (num >= nums2[nums2Size/2+1])
      return (double)(nums2[nums2Size/2] + nums2[nums2Size/2+1]) / 2.0;
    return (double)(num + nums2[nums2Size/2]) / 2.0;
  }  
}

/*  Given a sorted array a[n] and two separated integers, finds out
    the median of them.  This function requires n >= 3.  Constant time
    complexity.
*/
double median_by_interpolate_two(int *nums, int* nums2, int nums2Size) {
  int half = nums2Size / 2;
  if (nums2Size % 2 == 0) {
    return median_by_sort(nums, 2, nums2+half-2, 4);
  } else {
    return median_by_sort(nums, 2, nums2+half-1, 3);
  }
}

// Given a sorted array a[n] and zero, one, or two separated elements,
// finds out the median of them. It is required that n >= 3
double median_by_interpolate(int *nums1, int nums1Size, int* nums2, int nums2Size) {
  if (nums1Size == 0)
    return median(nums2, nums2Size);
  if (nums1Size == 1)
    return median_by_interpolate_one(nums1[0], nums2, nums2Size); 
  // assert nums1Size == 2;
  return median_by_interpolate_two(nums1, nums2, nums2Size);     
}

double findMedianSortedArrays(int* nums1, int nums1Size, int* nums2, int nums2Size){
  // median_by_interpolate requires that the sorted array size must be
  // at least 3. So for the case that each array has less than 2
  // elements in it, we directly sort them.
  if (nums1Size + nums2Size <= 4)
    return median_by_sort(nums1, nums1Size, nums2, nums2Size);
  if (nums1Size <= 2)
    return median_by_interpolate(nums1, nums1Size, nums2, nums2Size);
  else if (nums2Size <= 2)
    return median_by_interpolate(nums2, nums2Size, nums1, nums1Size);

  double median1, median2;
  
  median1 = median(nums1, nums1Size);
  median2 = median(nums2, nums2Size);
  if (median1 == median2)
    return median1;
  // normalize so that nums1's median is always the non-greater one:
  if (median1 > median2) { 
    int * tmp = nums1;
    int tmp_len = nums1Size;
    
    nums1 = nums2;
    nums1Size = nums2Size;
    nums2 = tmp;
    nums2Size = tmp_len;
  }

  int cut_size1 = nums1Size % 2 == 0 ? nums1Size/2 - 1 : nums1Size/2;
  int cut_size2 = nums2Size % 2 == 0 ? nums2Size/2 - 1 : nums2Size/2;
  // the number of elements that will be got rid of
  int cut_size = min(cut_size1, cut_size2);  
  int new_nums1Size, new_nums2Size;

  new_nums1Size = nums1Size - cut_size;
  nums1 += cut_size;
  new_nums2Size = nums2Size - cut_size;
  // nums2 stay unchanged
  // recursion:
  return findMedianSortedArrays(nums1, new_nums1Size, nums2, new_nums2Size);
}



int main() {
  /*
  int n = 3;
  int m = 20;
  int a[3] = {1, 10, 20};
  int b[20] = {2, 11, 21};

  for (int i = 3; i < 20; i++)
    b[i] = 21;
  */
  int n = 1;
  int m = 9;
  int a[1] = {10};
  int b[9] = {1,2,3,4,5, 6, 7,8, 9};
  
  double median = findMedianSortedArrays(a, n, b, m);

  printf("%.4f\n", median);
}
