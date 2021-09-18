#include "stdio.h"
#include "stdlib.h"

#define T int
#define LT_T(x, y) ((x) < (y))
#define GT_T(x, y) ((x) > (y))
#define SWAP_T(x, y) {T tmp = (x); (x) = (y); (y) = tmp;}

/*
  idea of partition:

  Let the array be "a b ...  piv ... x y".  There are two pointers left
  and right, initially pointing to piv, that move towards left and
  right, respectively.  

  "a b ... c ... piv ... d ... x y"
          left         right

  If left and right point to c and d, resp., if c > pivot and d <
  pivot, simply swap c and d and move both pointers.

  If c and d are in correct partitions, resp., then move both pointers.

  If only c is in the wrong partition, move the right pointer until it
  finds one that is also in the wrong partition then it can be swapped
  with c.  Vice versa for d in the wrong partition.

  If only c is in the wrong partition and the right pointer can no
  longer move, i.e.,

     "a b ... c ... d piv ... "
             left           right

  We want to move piv to left and put c at the original place of piv,
  i.e.,

     "a b ... d piv c ... "

  Note that all elements between c and piv are in the correct
  partition.  But we want to avoid to shift all of them to one cell
  left. So we do:
     swap piv and piv-1, obtain
     "a b ... c ... piv d ... "   // now piv is in the correct place
     swap d and c, obtain
     "a b ... d ... piv c ... "   // now [d .. piv-1] and c are all in correct partitions

  Note that if (piv-1) refers to c, we can directly swap piv and c.

  The left pointer just need to move one cell left then continue.  Vice versa for the case that 
  the left pointer can no longer move.

 */
int partition(T * a, int start, int end, int pivot) {
  int left = pivot, right = pivot; 
  
  while (left >= start || right < end) {
    if (left < start) {
      if (LT_T(a[right], a[pivot])) {
	if (pivot + 1 < right)
	  SWAP_T(a[pivot], a[pivot+1]);	  
	SWAP_T(a[pivot], a[right]);
	pivot++;
      }
      right++;
    } else if (right >= end) {
      if (GT_T(a[left], a[pivot])) {
	if (pivot - 1 > left)
	  SWAP_T(a[pivot], a[pivot-1]);
	SWAP_T(a[pivot], a[left]);
	pivot--;
      }
      left--;
    } else if (GT_T(a[left], a[pivot]) && LT_T(a[right], a[pivot])) {
      // swap left and right:
      SWAP_T(a[left], a[right]);
      left--; right++;
    } else if (GT_T(a[left], a[pivot])) 
      right++;
    else if (LT_T(a[right], a[pivot]))
      left--;
    else {
      left--; right++;
    }
  }
  return pivot;
}


// a: pointer to a sequence of Ts, where a part of it will be sorted
// start: inclusive start index of the sorting segment of "a"
// end: exclusive end index of the sorting segment of "a"
void quick_sort(T * a, int start, int end) {
  if (start + 1 >= end)
    return;
  
  int pivot = (end-start)/2 + start;
  
  pivot = partition(a, start, end, pivot);
  quick_sort(a, start, pivot);
  quick_sort(a, pivot, end);  
}

// test driver:
int main(int argc, char * argv[]) {
  int in[argc-1];
  
  for (int i = 0; i < argc-1; i++)
    in[i] = atoi(argv[i+1]);
  quick_sort(in, 0, argc-1);
  for (int i = 0 ; i < argc-1; i++)
    printf("%5d", in[i]);
  printf("\n");
}



