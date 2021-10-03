#include "string.h"

void setZeroes(int** matrix, int matrixSize, int* matrixColSize) {
  int nrows = matrixSize;
  int ncols = matrixColSize[0];
  int pr = -1, pc =-1;
  
  for (int i = 0; i < nrows; i++)
    for (int j = 0; j < ncols; j++)
      if (matrix[i][j] == 0) {
	if (pr < 0) {
	  // first 0 found ...
	  pr = i; pc = j;
	} else {
	  // project w.r.t pr and pc ...
	  matrix[pr][j] = 0;
	  matrix[i][pc] = 0;
	}
      }
  if (pr < 0)
    return;
  for (int i = 0; i < nrows; i++)
    if (i != pr && matrix[i][pc] == 0)
      memset(matrix[i], 0, sizeof(int) * ncols);
  for (int j = 0; j < ncols; j++)
    if (j != pc && matrix[pr][j] == 0)
      for (int i = 0; i < nrows; i++)
	matrix[i][j] = 0;
  memset(matrix[pr], 0, sizeof(int) * ncols);
  for (int i = 0; i < nrows; i++)
    matrix[i][pc] = 0;
}
