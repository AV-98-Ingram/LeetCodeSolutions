/*
 * 3. Longest Substring Without Repeating Characters.
 *
 * Observations:
 * ... a x ... a ...
 *  ^          ^
 *  |          | 
 * start     first-duplication
 *
 * The current exploring substring starts from "start" and encounters
 * the "first-duplication".  There is no duplication nor another 'a'
 * in between first 'a' and the "first-duplication".  Therefore, the
 * next exploring substring can start from the prefix "x ... a".
 *
 * A table recording unique characters in the currently exploring
 * substring is maintained.  In this implementation, the "table[c]" is
 * the address of the character 'c' in the given string or NULL.  The
 * position of 'c' in the given string can be obtained through pointer
 * subtraction.  Such find duplication is a constant-cost operation.
 *
 * Note that when the algorithm is about to explore the new substring
 * starting from "x ... a", records in table from "start" to the first
 * 'a' shall be cleaned.
 */

#include "string.h"
#include "stdio.h"

char* table[256] = {0};  // a table for quick looking up duplications

char * contains(char * c) {
  if (table[*c] != (void*)0)
    return table[*c];
  return (void*)0;
}

void mark(char * c) {
    table[*c] = c;
}

void clean(char * from, char * to) {
  char * i = from;
  
  while (i != to) {
    table[*i] = 0;
    i+=1;
  }
}

void cleanall() {
  memset(table, 0, sizeof(char*)*256);
}

int lengthOfLongestSubstring(char * s) {
  int len, max;
  char * curr = s;
  char * begin = curr;

  cleanall();
  len = 0; max = 0;
  while (*curr != '\0') {
    char * dup = contains(curr);

    if (dup != (void*)0) {
      // reaches a duplication:
      if (len > max) max = len;
      clean(begin, dup + 1);
      mark(curr);
      len = curr - dup;
      begin = dup + 1;
      curr += 1;
    } else {
      // no duplication:
      len += 1;
      mark(curr);
      curr += 1;
    }
  }
  return len > max ? len : max;
}

int main() {
  int x = lengthOfLongestSubstring("bbbbb");

  printf("%d\n", x);
  return 0;
}
