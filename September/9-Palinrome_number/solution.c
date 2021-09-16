#include "stdlib.h"
#include "stdio.h"

// pre-cond: -2^(31) <= x <= 2^(31) - 1
// the number of digits in x is less than 11
int isPalindrome(int x){  
  if (x < 0) return 0;
  if (x == 0) return 1;
  
  // extract digits:
  int digits[10];
  int i = 0;

  while (x > 0) {
    digits[i++] = x % 10;
    x = x / 10;    
  }
  // i hold the number of digits now
  int left, right;

  if (i % 2) {
    // odd:
    left = i / 2;
    right = left;
  } else {
    // even:
    left = i / 2 - 1;
    right = left + 1;
  }
  while (left >= 0 && right < i)
    if (digits[left--] != digits[right++])
      return 0;
  return 1;
}

int main(int argc, char * argv[]) {
  printf("%d\n", isPalindrome(atoi(argv[1])));
}
