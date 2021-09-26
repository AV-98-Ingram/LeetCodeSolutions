int reach(int r, int c, int m, int n, int (*cache)[n], int ** obs);

int uniquePathsWithObstacles(int** obstacleGrid, int obstacleGridSize, int* obstacleGridColSize){
  int m = obstacleGridSize;
  int n = obstacleGridColSize[0];
  int cache[m][n];
  
  memset(cache, -1, sizeof(int) * m * n);  
  return reach(0, 0, m, n, cache, obstacleGrid);
}


// returns #path from (r, c) to (m-1, n-1)
int reach(int r, int c, int m, int n, int (*cache)[n], int ** obs) {
  if (obs[r][c])
    return 0;
  if (r == m-1 && c == n-1)
      return 1;
  if (cache[r][c] >= 0)
    return cache[r][c];
  
  int npath = 0;
  
  if (r < m-1 && !obs[r+1][c])
    npath += reach(r + 1, c, m, n, cache, obs);
  if (c < n-1 &&  !obs[r][c+1])
    npath += reach(r, c + 1, m, n, cache, obs);
  cache[r][c] = npath;
  return npath;
}
