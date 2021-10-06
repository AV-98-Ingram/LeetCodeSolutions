
/*
  I didn't think of this idea: to keep track of what segment is
  "dominated" by which bar with a stack.  A bar dominates a segment if
  it is the lowest bar in the segment.  The stack has following
  properties:

    -- stack[i] >= stack[i-1] forall i > 0
    -- stack[top] dominates the range [stack[top-1] .. scan position], if top > 0;
                                      [0 .. scan position], otherwise.

  The algorithm works in the following way.

    1. scan the bars from left to right

    2. if the next bar is shorter than bar(stack[top]) or stack is
    empty, pop the stack until stack[top] >= the next bar or stack
    becomes empty.
      -- 2.1 during pop, computes the histogram size of the range
         dominated by each of the popped bars.
      -- 2.2 push the next bar onto the stack after the pops.

    3.  pop and compute for the remaining bars in the stack after the
    scan.
  
    
  For example, given bars [2, 3, 1, 6, 5, 2], right before the
  current_idx points to "1", the stack is {2} meaning that "2"
  dominates segment [0 .. 1].  After the current_idx pointing to "1",
  the stack pops "2" as it is smaller than "1" hence "1" is the new
  dominator for [0 .. 2].  After the current_idx pointing to "6", the
  stack is {1, 6} meaning that "6" dominates[3 .. 3] while "1"
  dominates [0 .. 3].
 
  
  
 */
#define H heights
#define SIZE heightsSize

int largestRectangleArea(int* heights, int heightsSize){
  int stack[SIZE];
  int top = 0;
  int max = H[0];

  stack[top] = 0;
  for (int i = 1; i <= SIZE; i++) {
    if (i == SIZE || H[i] < H[stack[top]]) {
      while (top>=0 && (i == SIZE || H[i] < H[stack[top]])) {
	int dom_bar = H[stack[top]];
	int dom_len = top == 0 ? i : (i - stack[top-1] - 1);
	int histo = dom_bar * dom_len;
	
	if (histo > max)
	  max = histo;	
	top--;	
      }
    }
    stack[++top] = i;  
  }  
  return max;
}

#include "stdio.h"

int main() {
  int a[10] = {5,5,1,7,1,1,5,2,7,6};

  printf("%d\n", largestRectangleArea(a, 10));
}
