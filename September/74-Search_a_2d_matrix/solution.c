#define NROWS matrixSize
#define NCOLS matrixColSize[0]

int b_search(int ** matrix, int start, int end, int target, char search_row, int r) {
  if (start == end - 1)
    return start;

  int mid = (end - start)/2 + start;
  int mid_val = search_row ? matrix[mid][0] : matrix[r][mid];

  if (mid_val > target)
    return b_search(matrix, start, mid, target, search_row, r);
  else
    return b_search(matrix, mid, end, target, search_row, r);
}

char searchMatrix(int** matrix, int matrixSize, int* matrixColSize, int target){
  int row = b_search(matrix, 0, NROWS, target, 1, 0);
  int col = b_search(matrix, 0, NCOLS, target, 0, row);

  return matrix[row][col] == target;
}
