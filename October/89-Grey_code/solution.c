#include "math.h"
#include "stdlib.h"
#include "stdio.h"

/*
  Idea: looking for the recursive pattern:
  Let '+' be array-concatenation. Let '@' be bit-concatenation.

  {1,2} + {3,4} = {1,2,3,4}
  1 @ 0b00 = 0b100
  
  f(1) = {0, 1}
  f(2) = f(1) + {11, 10} = {00, 01, 11, 10}
  f(3) = f(2) + {110, 111, 101, 100} = {000, 001, 011, 010, 110, 111, 101, 100}
  ...
  f(n) = f(n-1) + { '1' @ i | foreach i in f(n-1) in REVERSE-order}
  
 */

int f(int n, int * restrict p) ;

int* grayCode(int n, int* returnSize){
  *returnSize = pow(2, n);
  
  int * gray = (int*)malloc(*returnSize * sizeof(int));

  f(n, gray);
  return gray;
}

int f(int n, int * restrict p) {
  if (n == 1) {
    p[0] = 0;
    p[1] = 1;
    return 2;
  }
  
  int size = f(n-1, p);
  int setter = 1 << (n-1);
  int * restrict q = p + size;
  
  for (int i = size-1; i >= 0; i--)
    q[size-1-i] = p[i] | setter;
  return size << 1;
}

int main(int argc, char * argv[]) {
  int n = atoi(argv[1]);
  int size;
  int * p = grayCode(n, &size);

  for (int i = 0; i < size; i++)
    printf("%2d", p[i]);
  free(p);
  return 0;
}
