/* (0,0) (0,1) ... (0, n-1)
   (1,0) ...
   ...
   (n-1, 0) ...

   Clockwise 90 degree rotate makes (0,0) at (0,n-1). All other cells
   rotate accordingly, i.e., rotate((0+i, 0+j)) = (0+j, n-1-i).

   We will also need conter-clockwise rotate 90 degree, i.e.,
   de-rotate((i, j)) = (n-1-j, i)

   The algorithm works in such a way for an n by n matrix
   
   (0,0)  (0,1)  ...     (0,n-2) (0, n-1)
   (1,0)  (1,1)  ...     (1,n-2) (1, n-1)
   ...
   (n-1,0) ...

   Let write(i,j) be the appropriate way to write 4 cells:
   (i, j), rotate(i, j), rotate^2(i, j), and rotate^3(i, j).

   We when write(0, 0),   write(0, 1),   ...,   write(0, n-2),
              write(1, 1),      ...,        write(1, n-3),
                                ...    
                  write(n/2-1, n/2-1), ... write(n/2, n-n/2-1)

 */
void rotate_write(int i, int j, int count);

int N;
int ** MAT;

#define ROT(x, y)          (y), N-1-(x)
#define READ_DEROT(x, y)   MAT[N-1-(y)][(x)]

void rotate(int** matrix, int matrixSize, int* matrixColSize){
  MAT = matrix;
  N = matrixSize;
  
  int half = N/2;
  
  for (int i = 0; i < half; i++)
    for (int j = i+1; j < N-i; j++)
      rotate_write(i, j, 0);
}

void rotate_write(int i, int j, int count) {
  // rotate stop:
  if (count == 4)
    return;
  
  int my_val = MAT[N-1-j][i];    // read de-rotate(i, j)

  rotate_write(j, N-1-i, count+1); // go write rotate(i, j)
  MAT[i][j] = my_val;
}

#include "stdio.h"

int main() {
  int * x[3];
  int ** mat = &x[0];
  int m[3][3] = {{1,2,3},{4,5,6},{7,8,9}};

  for (int i = 0; i < 3; i++)
    mat[i] = m[i];
  
  rotate(mat, 3, (void*)0);
  
  for (int  i = 0; i < 3; i++)
    {
      for (int j = 0; j < 3; j++)
	printf("%2d", m[i][j]);
      printf("\n");
    }
}
