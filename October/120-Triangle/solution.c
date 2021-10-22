#define TS triangleSize
#define T triangle
#define MIN(x, y) ((x)>(y)?(y):(x))

// This is a DP problem but we dont need all the history,
// we only need the minimum sum of each position of the last row.

int minimumTotal(int** triangle, int triangleSize, int* triangleColSize) {
  int last_row[TS];
  
  last_row[0] = T[0][0];
  for (int i = 1; i < TS; i++) {
    int prev = last_row[0];
    
    last_row[0] = prev + T[i][0];    
    for (int j = 1; j < i+1; j++) {
      int curr = (j < i ? MIN(prev, last_row[j]) : prev) + T[i][j];
      
      prev = last_row[j];
      last_row[j] = curr;
    }
  }
  
  int min = last_row[0];
  
  for (int i = 1; i < TS; i++)
    if (last_row[i] < min)
      min = last_row[i];
  return min;
}
