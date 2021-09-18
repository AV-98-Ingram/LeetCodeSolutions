typedef struct Bar {
  int h;
  int pos;
} Bar;

#define T Bar
#define SWAP_T(x, y) {T tmp = (x); (x) = (y); (y) = tmp;}

int partition(T * a, int start, int end, int pivot) {
  int left = pivot, right = pivot; 
  
  while (left >= start || right < end) {
    if (left < start) {
      if (a[right].h < a[pivot].h) {
	if (pivot + 1 < right)
	  SWAP_T(a[pivot], a[pivot+1]);	  
	SWAP_T(a[pivot], a[right]);
	pivot++;
      }
      right++;
    } else if (right >= end) {
      if (a[left].h > a[pivot].h) {
	if (pivot - 1 > left)
	  SWAP_T(a[pivot], a[pivot-1]);
	SWAP_T(a[pivot], a[left]);
	pivot--;
      }
      left--;
    } else if (a[left].h > a[pivot].h && a[right].h < a[pivot].h) {      
      // swap left and right:
      SWAP_T(a[left], a[right]);
      left--; right++;
    } else if (a[left].h > a[pivot].h) 
      right++;
    else if (a[right].h < a[pivot].h) 
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

/* Intuition: 
   
   1. 
   Given a dent with a start and an end position, we can compute
   the water the dent can hold by 
    
        (end - start - 1) * min(height(start), height(end))  // size of the dent
        -  
	sum(start+1, end-1, lambda i: height(i))  // size of those "upheavals" in the dent

   Then, 2. 
   Given a list of bars, we could first find the biggest dent that can
   hold water by finding the max and second-max bars.  Then we find
   the third-max bar, which is either at left of, inside, or at right
   of the biggest dent.  If it is inside the dent, ignore
   it. Otherwise, it determines the second-largest dent which connects
   to the biggest dent.  So on and so forth, we can always use a pair
   of start and end positions to mark the dents we computed and take
   the max bar among the rest to compute for the next dent.   
 */


/* Computes the water in the dent from the "start" Bar to the "end" Bar. 
   The "shortBar" is the shorter one among "start" and "end".
*/
int dent(Bar start, Bar end, Bar shortBar, int * h);

int trap(int* height, int heightSize) {
  Bar bars[heightSize];
  
  for (int i = 0; i < heightSize; i++) {
    bars[i].h = height[i];
    bars[i].pos = i;
  }
  quick_sort(bars, 0, heightSize);
  
  if (heightSize <= 1)
    return 0;

  int start, end;
  int water = 0;
  int next = heightSize - 3;
  
  if (bars[heightSize-1].pos < bars[heightSize-2].pos)  {
    start = heightSize-1;
    end = heightSize-2;
  } else {
    start = heightSize-2;
    end = heightSize-1;
  }
  water += dent(bars[start], bars[end], bars[heightSize-2], height);
  while (next >= 0) {
    if (bars[next].pos < bars[start].pos) {
      water += dent(bars[next], bars[start], bars[next], height);
      start = next;
    } else if (bars[next].pos > bars[end].pos) {
      water += dent(bars[end], bars[next], bars[next], height);
      end = next;
    } 
    next--;
  }
  return water;
}

int dent(Bar start, Bar end, Bar shortBar, int * h) {
  int water = (end.pos - start.pos - 1) * shortBar.h;
  
  for (int i = start.pos+1; i < end.pos; i++)
    water -= h[i];
  return water;
}

#include "stdio.h"
#include "stdlib.h"

int main(int argc, char * argv[]) {  
  int height[argc-1];

  for (int i = 0; i < argc-1; i++)
    height[i] = atoi(argv[i+1]);
  printf("%d\n", trap(height, argc-1));
}


