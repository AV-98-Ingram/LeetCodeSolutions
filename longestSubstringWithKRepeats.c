/* Description : Find the length of the longest substring T of a given
 * string (consists of lowercase letters only) such that every
 * character in T appears no less than k times.
 *
 * Example: 
 * Input: s = "aaabb", k = 3
 * Output: 3 
 * the valid substring is "aaa"
 *
 * Basic Idea: Splits the given string into segments by the characters
 * that in total appear less than k times.  Naming these characters
 * "blockers".  A valid substring must not contain any blocker.
 * Hence, the longesting valid substring can only be a substring of
 * one of the segments.  For each segment s, do simple repeatitive
 * scanning. For each scan, a character-appearance-counter array and
 * the number of scanned characters that appear less than k times are
 * maintained.
 */


#include "stdlib.h"
#include "string.h"
#include "stdio.h"

#define CODE(x) ((int)(x) - (int)'a')

// Sets table[CODE(c)] to 1 iff a lower-case letter character c
// appears less than k times in the given string ...
void charsLessThanK(char * str, int * table, int k) {
  int iter = 0;
  int counts[30];

  memset(counts, 0, sizeof(int) * 30);
  while (str[iter] != '\0') {
    if (++counts[CODE(str[iter])] >= k) 
      table[CODE(str[iter])] = 0;
    else
      table[CODE(str[iter])] = 1;
    iter += 1;
  }
}

// Checks the substring starting from the given start position and
// ending at the first encountered such character c that
// table[CODE(c)] == 1. The position of c is assigned to what 'end'
// refers.
// Returns the length of the longest prefix of the checking substring
// that contains characters which all appear at least k times.
int charsMoreThanK(char * str, int start, int * end, int * table, int k) {
  int numCharsLessThanK = 0;
  int counts[30];
  int iter = start;
  int lastSatisfiedPos = start; // the prefix from start to lastSatisfiedPos is the latest one satisfies the requirement.
  
  memset(counts, 0, sizeof(int) * 30);
  while (str[iter] != '\0' && table[CODE(str[iter])] != 1) {
    if (counts[CODE(str[iter])]++ == 0) 
      numCharsLessThanK += 1;
    if (counts[CODE(str[iter])] == k) 
      numCharsLessThanK -= 1;
    iter += 1;
    if (numCharsLessThanK == 0) 
      lastSatisfiedPos = iter;
  }
  *end = iter;
  return lastSatisfiedPos - start;
}


// Repeatedly check substrings of each segment of the given string s.
// Segments in s are divided by the characters, which appear in the
// string less than k times in total.
int longestSubstring(char * s, int k) {
  int table[30];

  charsLessThanK(s, table, k);
  int max = 0;
  int start = 0;
  int len, end;
  
  do {
    len = charsMoreThanK(s, start, &end, table, k);
    if (len > max) 
      max = len;
    if (len >= end - start)
      start = end + 1;  // already found the longest prefix of s[start .. end-1] satisfying the requirement
    else
      start += 1;       // s[start .. end-1] may have valid substring that is longer than the found prefix (or nothing found)
  } while (s[end] != '\0');
  return max;
}


int main(int argc, char ** argv) {
  char * s = argv[1];
  int k = atoi(argv[2]);

  printf("input = %s, k = %d\n", s, k);

  int r = longestSubstring(s, k);

  printf("result = %d\n", r);
}
