/*  This is an improved solution to solution1.c with less code.  This
    solution combines the functions for odd and even cases in
    solution1.c to a generic function for any case.
 */
#include <string.h>
#include <stdio.h>

#define min(x, y)  ((x) > (y) ? (y) : (x))

int max = 1, ret_l = 0, ret_r = 0;

// pre-cond: (right + 1 == left) || (left == right)
void palind(char * s, int left, int right, int len) {
  int isEven = right - left;
  int possible_max;
  int size = 0;
  
  if (isEven)
    possible_max = min(left + 1, len - right) * 2;
  else
    possible_max = min(left, len - right - 1) * 2 + 1;
  if (possible_max <= max)
    return; // optimization: no need to compute as it can go beyound current max
  
  while (left >= 0 && right < len && s[left] == s[right]) {
    size += 2; left -= 1; right += 1;
  }
  size -= (!isEven);
  if (size > max) {
    max = size;
    ret_l = left + 1;
    ret_r = right - 1;
  }
}

char * longestPalindrome(char * s){
  int len = strlen(s);
  int left = len / 2;
  int right = left + 1;
  
  while (left >= 0) {
    palind(s, left, left, len);
    if (left + 1 < len)
      palind(s, left, left + 1, len);
    left--;
  }
  while (right < len) {
    palind(s, right, right, len);
    palind(s, right - 1, right, len);
    right++;
  }
  s[ret_r + 1] = '\0';
  return s + ret_l;
}

int main(int argc, char * argv[]) {
  char * in = argv[1];
  char * out = longestPalindrome(in);

  
  printf("%s\n", out);
}
