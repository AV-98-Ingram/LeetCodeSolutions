/* C's memory region based bit testing outperforms Java's BitSet
   solution quite a lot. 
   
   The idea is simple: try all possible divisions and cache results.
   When test if it worth to recursively check two string segments,
   e.g., "abc" and "cba", first check if they contain same amount of
   characters. Here is where the bit-based testing comes to play.
*/

#include "string.h"

#define NBYTES ((30*26 / 8)+1)

char f(char* s1, int begin1, int end1, char* s2, int begin2, int end2);
void incr(char c, char * bs);
char compare_bs(char * bs1, char * bs2);

char cache[31][31][31][31]; // strig length <= 30

char isScramble(char * s1, char * s2) {
  int len = strlen(s1);
  
  if (len != strlen(s2))
    return 0;
  memset(cache, -1, 31*31*31*31);
  return f(s1, 0, len, s2, 0, len);
}

char f(char * s1, int begin1, int end1, char * s2, int begin2, int end2) {
  if (begin1 == end1 - 1)
    return s1[begin1] == s2[begin2];

  if (cache[begin1][end1][begin2][end2] >= 0)
    return cache[begin1][end1][begin2][end2];
  
  char bs1[NBYTES] = {0};
  char bs_ns2[NBYTES] = {0};
  char bs_s2[NBYTES] = {0};
  
  // if only s1[0 .. len) matches s2[0 .. len), it's a fail:
  for (int d = begin1; d < end1 - 1; d++) {
    incr(s1[d], bs1);
    // for not swap:
    incr(s2[begin2 + (d - begin1)], bs_ns2);
    // for swapped
    incr(s2[end2 - 1 - (d - begin1)], bs_s2);
    if (compare_bs(bs1, bs_ns2))
      if (f(s1, begin1, d + 1, s2, begin2, begin2 + (d - begin1) + 1))
	if (f(s1, d + 1, end1, s2, begin2 + (d - begin1) + 1, end2)) {
	  cache[begin1][end1][begin2][end2] = 1;
	  return 1;
	}
    if (compare_bs(bs1, bs_s2))
      if (f(s1, begin1, d + 1, s2, end2 - (d - begin1) - 1, end2))
	if (f(s1, d + 1, end1, s2, begin2, begin2 + (end1 - d - 1))) {
	  cache[begin1][end1][begin2][end2] = 1;
	  return 1;
	}
  }
  cache[begin1][end1][begin2][end2] = 0;
  return 0;
}

void incr(char c, char * bs) { // size(bs) = 30 * 26 bits
  int ci = c - 'a';
  int idx = ci / 8;
  int oft = ci % 8;
  char tester = 1 << (8-oft-1);


  while (bs[idx] & tester) {
    ci += 26;
    idx = ci / 8;
    oft = ci % 8;
    tester = 1 << (8-oft-1);    
  }
  bs[idx] |= tester;
}

char compare_bs(char * bs1, char * bs2) {
  return !memcmp(bs1, bs2, NBYTES);
}

#include "stdio.h"

int main() {
  char * a = "eebaacbcbcadaaedceaaacadccd";
  char * b = "eadcaacabaddaceacbceaabeccd";

  printf("%d", isScramble(a, b));
}
