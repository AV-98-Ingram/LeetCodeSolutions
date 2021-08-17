/*
  To solve this problem, it only takes to follow the given algorithm
  given in the description.
  
  The lesson I learned from this problem, which I failed to be aware
  of from Problem 7 "Reverse Integer", is that when I need to use a
  32-bit integer to hold -2147483648 (MIN value), I cannot let it to
  first hold 2147483648 then negate it because it can only hold
  maximum 2147483647.
 */

#include "stdio.h"
#include "stdint.h"

#define BOUND 214748364  // positive max := BOUND * 10 + 7
              

int myAtoi(char * s){
  char pos = 1;
  
  // skip while spaces:
  while (*s == ' ')
    s+=1;
  // read the sign:
  if (*s == '+')
    s+=1;
  else if (*s == '-') {
    pos = 0;
    s+=1;
  }
  
  char digit_start = '0';
  char digit_end = '9';
  int32_t val = 0;
  
  while (digit_start <= *s && *s <= digit_end) {
    int32_t digit = *s - digit_start;
    
    if (pos) {
      if (val > BOUND || (val == BOUND && digit > 7))
	return BOUND * 10 + 7;
      val = val * 10 + digit;      
    } else {
      if (val < -BOUND || (val == -BOUND && digit > 8))
	return -(BOUND * 10 + 8);
      val = val * 10 - digit;
    }    
    s++;
  }
  return val;
}


int main(int argc, char * argv[]) {
  printf("%d\n", myAtoi(argv[1]));
}
