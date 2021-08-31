#include "string.h"
#include "stdlib.h"

char * longestCommonPrefix(char ** strs, int strsSize) {
  if (strsSize == 0)
    return "";
  
  int min_str_len = strlen(strs[0]);
  for (int i = 1; i < strsSize; i++) {
    int str_len = strlen(strs[i]);

    if (str_len < min_str_len)
      min_str_len = str_len;    
  }

  char * result = (char*)malloc(min_str_len + 1);
  char stop = 0;
  
  memset(result, 0, min_str_len + 1);
  for (int i = 0; i < min_str_len; i++) {
    char common = strs[0][i];

    for (int j = 1; j < strsSize; j++)
      if (common != strs[j][i]) {
	stop = 1;
	break;
      }
    if (stop)
      break;
    result[i] = common;
  }
  return result;
}

int main() {
  char * x = "";
  longestCommonPrefix(&x, 1);
}
