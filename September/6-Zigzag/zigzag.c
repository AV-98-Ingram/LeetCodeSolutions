/* Solve by observing the pattern.

   Let r be the number of rows.  When zigzag printing, at i-th row,
   the characters will be
   
   s[i], 

   (0 < i < r-1 ? s[i + (r - i)*2 - 2] : nil), 

   s[i + r*2 - 2],                                     // let j = i + r*2 - 2 after this row

   (0 < i < r-1 ? s[j + (r - i)*2 - 2] : nil), 

   s[j + r*2 - 2],                                     // let j = j + r*2 - 2 after this row
   ...

   until the index goes out of bound.
 */
#include <string.h>
#include <stdlib.h>
#include <stdio.h>

void print_row(char * s, int s_len, int row_idx, int num_rows, char ** out);

char * convert(char * s, int numRows){
  int len = strlen(s);
  char * ret = (char*)malloc(len);
  char * p = ret;

  if (numRows == 1)
    return s;
  for (int i = 0; i < numRows; i++) 
    print_row(s, len, i, numRows, &p);
  return ret;
}


// pre-cond: 0 <= row_idx < num_rows && num_rows > 1
void print_row(char * s, int s_len, int row_idx, int num_rows, char ** out) {
  int i = row_idx; // var naming follow the comment at head
  int j = i;       // var naming follow the comment at head
  int idx = i;
  int big_step = num_rows * 2 - 2;          // NOTE that both steps could be 0 if num_rows = 1. If steps are 0, the loop below
  int small_step = (num_rows - i) * 2 - 2;  // will not work correctly
  char try_small_step = 1;
  
  while (idx < s_len) {
    **out = s[idx];
    (*out) = *out + 1;
    if (0 < i && i < num_rows-1 && try_small_step) {
      idx = j + small_step;
      try_small_step = 0;
    } else {
      idx = j + big_step;
      j = idx;
      try_small_step = 1;
    }     
  }
}


int main(int argc, char * argv[]) {
  printf("%s\n", convert(argv[1], atoi(argv[2])));
}
