/* 
   For a matrix, e.g.,
   0 1 0 1 1
   1 0 0 1 0
   1 0 0 1 0

   We can see each row as a list of bars, i.e.,

   row 0: 0 1 0 1 1
   row 1: 1 0 0 2 0
   row 2: 2 0 0 3 0

   The problem then can be converted to compute the maximal histogram
   of each row.  O(nc * nr^2).  It will be easier to transpose the
   matrix when nr >> nc if the input is an actual array instead of
   dumb pointer-to-pointer shit. Stupid LeetCode.   
 */

int histogram(char **m, int r, int nr, int nc) ;

#define NROW matrixSize
#define NCOL matrixColSize[0]

//int max = 0;  // DUMB LeetCode somehow treats C global variables as static ones.

int maximalRectangle(char** matrix, int matrixSize, int* matrixColSize) {    
  int max = 0;
  
  for (int i = NROW - 1; i >= 0; i--) {
    if ((i+1) * NCOL <= max)
      break; // no need continue
    
    int max_histo = histogram(matrix, i, NROW, NCOL, max);

    if (max_histo > max)
      max = max_histo;    
  }
  return max;
}

int histogram(char **m, int r, int nr, int nc, int g_max) {
  int bars[nc];
  int highest = 0;
  
  for (int i = 0; i < nc; i++) {
    bars[i] = 0;
    for (int j = r; j >= 0 && m[j][i] == '1'; j--)
      bars[i]++;
    if (bars[i] > highest)
      highest = bars[i];
  }
  if (highest * nc <= g_max)
    return 0;  // early return optimization

  int stack[nc];
  int top = 0;
  int max = 0;  //  local max
  
  stack[top] = 0;
  for (int i = 1; i <= nc; i++) {
    if (i == nc || bars[i] < bars[stack[top]]) {
      while (top >= 0 && (i == nc || bars[i] < bars[stack[top]])) {
	int dom_bar = bars[stack[top]];
	int dom_len = top > 0 ? i - stack[top-1] - 1 : i;
	int rec = dom_bar * dom_len;

	if (max < rec)
	  max = rec;
	top--;
      }
    } 
    stack[++top] = i;
  }
  return max;
}

#include "stdio.h"

int main() {
  int m = 4, n = 5;
  char ma[4][5] = {{'1','0','1','1','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},
		   {'1','0','1','1','0'}};
  char * tmp[m];
  char **mp = tmp;

  for (int i = 0; i < m; i++)
    tmp[i] = ma[i];
  printf("%d\n", maximalRectangle(mp, m, &n));
}
