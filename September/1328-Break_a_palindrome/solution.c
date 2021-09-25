#include "string.h"

char * breakPalindrome(char * palindrome){
  int len = strlen(palindrome);
  int left_end = len / 2 - 1;
  char * p = palindrome;

  if (left_end < 0)
    return "";
  for (int i = 0; i <= left_end; i++) {
    if (p[i] != 'a') {
      p[i] = 'a';
      return p;
    }
  }
  p[len-1] = 'b';
  return p;
}

#include "stdio.h"

int main(int argc, char * argv[]) {
  printf("%s\n",breakPalindrome(argv[1]));
}
