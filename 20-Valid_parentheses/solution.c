#include "stdio.h"

int cast(char c) {
  switch(c) {
  case ')':  return -(int)'(';
  case ']':  return -(int)'[';
  case '}':  return -(int)'{';
  default:
    return (int)c;
  }
}

char isValid(char * s){
  int stack[10000] = {0};
  int top = -1;
  
  while (*s != 0) {
    int c = cast(*(s++));
    
    if (c < 0) {
      // pop and match:
      if (top < 0)
	return 0;
      if (stack[top--] + c != 0)
	return 0;     
    } else {
      // push:
      stack[++top] = c;
    }
  }
  return top < 0;
}

int main(int argc, char *argv[]) {
  printf("%d\n", isValid(argv[1]));
}
