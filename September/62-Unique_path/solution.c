int reach(int r, int c, int m, int n, int (*cache)[n]);

int uniquePaths(int m, int n) {
  int cache[m][n];

  memset(cache, 0, sizeof(int) * m * n);  
  return reach(0, 0, m, n, cache);
}

// returns #path from (r, c) to (m-1, n-1)
int reach(int r, int c, int m, int n, int (*cache)[n]) {
  if (r == m-1 && c == n-1)
    return 1;
  if (cache[r][c] > 0)
    return cache[r][c];
  
  int npath = 0;
  
  if (r < m-1)
    npath += reach(r + 1, c, m, n, cache);
  if (c < n-1)
    npath += reach(r, c + 1, m, n, cache);
  cache[r][c] = npath;
  return npath;
}
