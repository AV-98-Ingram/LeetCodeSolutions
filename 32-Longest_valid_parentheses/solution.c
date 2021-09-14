/*
  Maintaining a stack of unmatched '('s.  When a '(' is matched and poped, push a 
  match mark "m(l)" onto the stack, where 'l' is the matched length. 

  When pop, if the top entry is a match mark, pop the mark first and
  connects the segment represented by the mark. Until the top is not a
  match mark, pop the '(' and then push a match mark representing the
  new match connected with all the segments represented by the popped
  marks. 
  
 */

#include "stdlib.h"

typedef struct List {
  int vlen;
  char is_marker;
  struct List * next;
} List;


// returns a pointer to the newly created node
List * push(List * l, char is_marker, int vlen) {
  List * node = (List *)malloc(sizeof(List));

  node->vlen = vlen;
  node->is_marker = is_marker;
  node->next = l;
  return node;
}

List * pop(List * l) {
  List * top = l->next;

  free(l);
  return top;
}

int longestValidParentheses(char * s) {
  List * l = NULL;
  int max = 0;
  
  while (*s != 0) {
    if (*s == '(') {
      l = push(l, 0, 0); // push '(';
    } else if (*s == ')') {
      int vlen = 0;
      
      while (l != NULL && l->is_marker) {
	vlen += l->vlen;
	l = pop(l);
      }
      if (l != NULL) {
	l = pop(l);
	vlen += 2;
	l = push(l, 1, vlen); // push mark(vlen)
      }
      if (vlen > max)
	max = vlen;
    }
    s++;
  }

  // finally, clear the stack and connects all the remaining markers

  
  while (l != NULL) {
    int vlen = 0;

    while (l != NULL && l->is_marker) {
      vlen += l->vlen;
      l = pop(l);
    }
    if (vlen > max)
      max = vlen;
    if (l != NULL)
      l = pop(l);
  }
  return max;
}

#include "stdio.h"

int main(int argc, char * argv[]) {
  printf("%d\n", longestValidParentheses(argv[1]));
}
