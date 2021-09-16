/* The core idea is:
   Recursively visiting a binary tree in pre-order.  If the input
   terminates before the visit being done, or the visit is done while
   the input still have remainings, the input is invalid.

   One of hinder of this problem is that one must pre-process the
   input string.  If using Java, one can easily split the string by
   ','.  I'm using C here so I wrote my own preprocessing function.   
*/

#include "stdio.h"
#include "stdlib.h"
#include "string.h"

#define EMPTY -1  // '#'
#define TERM -2   // '\0'

char check(int ** p) {
  int child = *(*p)++;
  
  if (child == TERM) 
    return 0;
  if (child == EMPTY) 
    return 1;        
  if (check(p))       // check left 
    return check(p);  // check right  
  return 0;
}

int * preproc(char *preorder) {
  int counter = 0;
  int tmp[strlen(preorder)];
  char * start, * end;
  
  start = preorder;
  end = preorder;
  while (*end != 0) {
    if (*end == ',') {
      *end = 0;
      if (*start != '#')
	tmp[counter++] = atoi(start);
      else
	tmp[counter++] = EMPTY;
      start = end + 1;
      end = end + 1;
    } else
      end++;
  }
  // process the last segment:
  if (*start != '#')
    tmp[counter++] = atoi(start);
  else
    tmp[counter++] = EMPTY;
  
  int * ret = (int*)malloc(sizeof(int) * (counter+1));
  
  memcpy(ret, tmp, sizeof(int) * counter);
  ret[counter] = TERM;
  return ret;
}

char isValidSerialization(char * preorder) {
  int * p = preproc(preorder);
  int root = *p;
  char result = 0;
  int * q = p + 1;

  if (root == EMPTY)
    result = 1;
  else {
    if (check(&q))        // check left 
      result = check(&q); // check right  
  }
  if (result)                      // check no extra nodes remaining
    result &= (*q == TERM);
  free(p);
  return result;
}

int main(int argc, char * argv[]) {
  printf("%d\n", isValidSerialization(argv[1]));
}
