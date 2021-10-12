#include "string.h"
#include "stdio.h"

char g(char flag, char finish, char *s1, int pos1, char * s2, int pos2, char * s3, int pos3);
int f(char *s1, int pos1, char *s2, int pos2);

int len1, len2, len3;
char (*cache)[101][101];

char isInterleave(char * s1, char * s2, char * s3){
  len1 = strlen(s1);
  len2 = strlen(s2);
  len3 = strlen(s3);

  char cache_phys[2][101][101];

  cache = cache_phys;
  memset(cache_phys, 0, 20000);
  return g(1, 0>=len1, s1, 0, s2, 0, s3, 0) || g(0, 0>=len2, s1, 0, s2, 0, s3, 0);
}


char g(char flag, char finish, char *s1, int pos1, char * s2, int pos2, char * s3, int pos3) {
  int max; // max length of the next interleaved sub-string

  if (cache[flag][pos1][pos2])
    return 0;
  if (flag) {
    max = f(s1, pos1, s3, pos3);
    if (finish)
      return pos1 + max == len1 && pos3 + max == len3 ? 1 : 0;
    for (int i = 1; i <= max; i++)
      if (g(1-flag, pos1+i >= len1, s1, pos1+i, s2, pos2, s3, pos3+i))
	return 1;
    cache[flag][pos1][pos2] = 1;
    return 0;
  } else {
    max = f(s2, pos2, s3, pos3);
    if (finish)
      return pos2 + max == len2 && pos3 + max == len3 ? 1 : 0;
    for (int i = 1; i <= max; i++)
      if (g(1-flag, pos2+i >= len2, s1, pos1, s2, pos2+i, s3, pos3+i))
	return 1;
    cache[flag][pos1][pos2] = 1;
    return 0;
  }
}

int f(char * s1, int pos1, char * s2, int pos2) {
  int ct = 0;
  while (s1[pos1]!=0 && s2[pos2]!= 0 && s1[pos1++] == s2[pos2++])
    ct++;
  return ct;
}

int main() {  
  printf("%d\n", isInterleave("aabcc", "dbbca", "aadbbcbcac"));
}
