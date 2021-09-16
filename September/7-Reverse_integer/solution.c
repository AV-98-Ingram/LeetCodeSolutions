#include "stdlib.h"
#include "stdint.h"
#include "stdio.h"

typedef struct List {
  struct List * next;
  int32_t val;
} List;

List * push(List *lst, int32_t val) {
  List * top = (List *)malloc(sizeof(List));

  top->val = val;
  top->next = lst;
  return top;
}

List * pop(List *lst, int32_t *val) {
  *val = lst->val;

  List * new_top = lst->next;

  free(lst);
  return new_top;
}

int reverse(int x){
  if (x < -2147483647)           // BE CAREFUL when negate a 32-bit integer (assuming no type larger than 32-bit)
    return 0;
  
  int32_t pos = x >= 0;
  int32_t rem = pos ? x : -x;

 
  List * stack = NULL;
  
  while (rem > 0) {
    int32_t digit = rem % 10;
    
    stack = push(stack, digit);
    rem = rem / 10;    
  }
  
  int32_t re = 0;
  int32_t factor = 1;
  while (stack != NULL) {
    int32_t val;
    
    stack = pop(stack, &val);
    if (factor > 100000000 && val > 2) // BE CAREFUL that val * factor could go out of bounds
      return 0;
    val = val * factor;
    if (re > 2147483647 - val)         // BE CAREFUL that re + val  could go out of bounds
      return 0;
    re += val;
    if (stack != NULL)                 // BE CAREFUL that when exiting loop, the final factor could go out of bounds
      factor *= 10;
  }
  return pos ? re : -re;
}

int main(int argc, char * argv[]) {
  printf("%d\n", reverse(atoi(argv[1])));
}
