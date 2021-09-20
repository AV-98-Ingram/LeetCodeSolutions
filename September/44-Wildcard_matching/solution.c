#include "stdio.h"
#include "stdlib.h"
#include "string.h"

#define IS_STAR(c) ((c) == '*')
#define IS_QEMK(c) ((c) == '?')

char * s_orig, * p_orig;
int p_len, s_len;

char match(char * s, char *p, char * cache);

char isMatch(char * s, char * p){
  p_len = strlen(p);
  s_len = strlen(s);
  s_orig = s;
  p_orig = p;

  char cache[s_len][p_len];  // NOTE THAT DUMB LEETCODE cannot accept s_len == 0 || p_len == 0
                             // they probably do interpretation on C instead of compile
  
  memset(cache, 0, (s_len) * (p_len));
  return match(s, p, &cache[0][0]);
}

char match(char * s, char *p, char * cache) {
  char (* seenStates)[p_len] = (char (*)[p_len]) cache;
  
  if (*s == 0 && *p == 0)
    return 1;
  else if (*p == 0)
    return 0;
  else if (*s == 0) {
    while (IS_STAR(*p))
      p++;
    return *p == 0;
  }

  int s_idx = s - s_orig, p_idx = p - p_orig;
  
  if (seenStates[s_idx][p_idx])
    return 0;
  if (IS_STAR(*p)) {
    if (match(s+1, p+1, cache)) // match any letter and proceed
      return 1;
    if (match(s+1, p, cache)) // match any letter but not proceed
      return 1;
    if (match(s, p+1, cache))  // match empty 
      return 1;
  } else if (IS_QEMK(*p) || *s == *p) {
    if (match(s+1, p+1, cache))
      return 1;    
  } 
  seenStates[s_idx][p_idx] = 1;
  return 0;
}

int main(int argc, char * argv[]) {
  char * s = argv[1];
  char * p = argv[2];

  printf("%d\n", isMatch(s, p));
}
