#include "stdlib.h"
#include "stdio.h"
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* spiralOrder(int** matrix, int matrixSize, int* matrixColSize, int* returnSize) {
  int roll = 0;
  int nrows = matrixSize;
  int ncols = matrixColSize[0];
  int * result  = (int *)malloc(sizeof(int) * nrows * ncols);
  int i = 0;
  
  while (1) {
    // top
    if (ncols - roll > roll)
      for (int j = roll; j < ncols - roll; j++)
	result[i++] = matrix[roll][j];
    else
      break;
    // right
    if (nrows - roll > roll + 1)
      for (int j = roll+1; j < nrows - roll; j++)
	result[i++] = matrix[j][ncols - roll - 1];
    else
      break;
    // bottom
    if (ncols - roll - 1 > roll)  // [nrows - roll - 1][roll <- ncols - roll - 2] 
      for (int j = ncols - roll - 2; j >= roll; j--)
	result[i++] = matrix[nrows - roll - 1][j];
    else
      break;
    // left
    if (nrows - roll - 1 > roll + 1) // [roll + 1 <- nrows - roll - 2][roll]
      for (int j = nrows - roll - 2; j >= roll + 1; j--)
	result[i++] = matrix[j][roll];      
    else
      break;    
    roll++;
  }
  *returnSize = nrows * ncols;
  return result;
}

int main() {
  int matrix[3][4] = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
  int * q[3];
  int ** p = q;
  int colSize[3] = {4,4,4};
  int x;
  for (int i = 0; i < 3; i++)
    p[i] = &matrix[i][0];

  int * ret = spiralOrder(p, 3, colSize, &x);

  for (int i = 0; i < 12; i++)
    printf("%3d", ret[i]);

  free(ret);
}
