/* This is the solution I came up the first time.  It is in theory
   same as the "best" solutions on LeetCode.  The idea is to find
   palindrome from centers.  For a string of size n, there are 2*n - 1
   centers as for even size palindromes, the center is in between two
   characters.  

   One of the drawback of this solution is that it breaks the cases
   into odd palindrome and even palindrome.  It is unnecessary and
   makes the code too complicated to be finished in a short time.
   The version deals with generic cases is in solution2.c.

   Finally, I NEED TO BE CAREFULL WITH calculating bookkeeping
   numbers.
 */

#include <string.h>
#include <stdio.h>

int palind_odd(char * s, int mid, int len, int max) {
  int size = 1;

  if (mid * 2 + 1 <= max)
    return 1;
  else if ((len - mid) * 2 - 1 <= max)
    return 1;

  int i = mid;
  int j = mid;

  while (i > 0 && j < len - 1 && s[--i] == s[++j])
    size += 2;
  return size;
}

int palind_even(char *s, int mid, int len, int max) {
  int size = 0;

  if ((mid + 1) * 2 <= max)   // e.g.,  if mid = 1, then the max possible solution could be 0 1 2 3  (these are indices).
    return 0;
  if ((len - 1 - mid) * 2 <= max) // e.g.,  if mid = 1 /\ len = 3, the max possible solution could be 1 2.
    return 0;

  int i = mid;
  int j = mid + 1;

  while (i >= 0 && j < len && s[i--] == s[j++])
    size += 2;
  return size;
}


char * longestPalindrome(char * s){
  int len = strlen(s);
  int max = 1;
  int mid = len / 2;
  int left = mid, right = left+1;
  int result_mid = 0;
    
  while (left >= 0) {
    int size = palind_odd(s, left, len, max);
    
    if (size > max) {
      max = size;
      result_mid = left;
    }
    size = palind_even(s, left, len, max);
    if (size > max) {
      max = size;
      result_mid = left;
    }
    left-=1;
  }
  while (right < len) {
    int size = palind_odd(s, right, len, max);
    
    if (size > max) {
      max = size;
      result_mid = right;
    }
    size = palind_even(s, right, len, max);
    if (size > max) {
      max = size;
      result_mid = right;
    }
    right++;
  }

  int start;

  if (max % 2 == 0) {
    start = result_mid + 1 - (max/2);
  } else {
    start = result_mid - (max/2);
  }
  s[start + max] = '\0';
  return s + start;
}

int main(int argc, char * argv[]) {
  char * in = argv[1];
  char * out = longestPalindrome(in);

  
  printf("%s\n", out);
}
