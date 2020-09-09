/* Description (1568. Minimum Number of Days to Disconnect Island)

   Given a 2D grid consisting of 1s (land) and 0s (water).  An island
   is a maximal 4-directionally (horizontal or vertical) connected
   group of 1s.

   The grid is said to be connected if we have exactly one island,
   otherwise is said disconnected.

   In one day, we are allowed to change any single land cell (1) into
   a water cell (0).

   Return the minimum number of days to disconnect the grid.



   Basic idea: For any island, it takes at most 2 days to disconnect
   it.  For example:

   1 1   ->   1 0  or  1 1 1 -> 1 0 1 or  1 1 1 -> 1 0 1
   1 1        0 1                           1        1

   Therefore, NO recursive exploration is EVER NEEDED.  Just check for 
   1) if the grid is initailly disconnected, and
   2) if the grid can be disconnected in 1 day.
   If neither of the above is satisfied, then it takes 2 days.

   Stupid problem!
 */
#include<string.h>
#include<stdlib.h>
#include<stdio.h>

int numRows, maxCols;

int isInnerLand(int r, int c, int ** grid, int gridSize, int * gridColSize) {
  
  if (r - 1 >= 0 && c - 1 >= 0 && r + 1 < gridSize && c + 1 < gridColSize[r])
    return grid[r-1][c] && grid[r-1][c-1] && grid[r-1][c+1] && grid[r+1][c] && grid[r+1][c-1] &&
      grid[r+1][c+1] && grid[r][c-1] && grid[r][c+1];
  return 0;
}

int numReachablesWorker(int r, int c, int **grid, int gridSize,
			int *gridColSize, char (*seen)[maxCols]) {
  if (seen[r][c] || !grid[r][c])
    return 0;

  seen[r][c] = 1;

  int result = 1;
  
  if (r - 1 >= 0)
    result += numReachablesWorker(r-1, c, grid, gridSize, gridColSize, seen);
  if (r + 1 < gridSize)
    result += numReachablesWorker(r+1, c, grid, gridSize, gridColSize, seen);
  if (c - 1 >= 0)
    result += numReachablesWorker(r, c-1, grid, gridSize, gridColSize, seen);
  if (c + 1 < gridColSize[r])
    result += numReachablesWorker(r, c+1, grid, gridSize, gridColSize, seen);
  return result;
}

int numReachables(int r, int c, int **grid, int gridSize,
		  int *gridColSize) {
  char seen[numRows][maxCols];
  
  memset(seen, 0, sizeof(char) * numRows * maxCols);
  return numReachablesWorker(r, c, grid, gridSize, gridColSize, seen);
}

int tryDisconnect(int r, int c, int** grid, int gridSize, int* gridColSize,
		  int islandSize) {   
  if (isInnerLand(r, c, grid, gridSize, gridColSize))
    return 3;

  grid[r][c] = 0;

  int reaches, nextR = r, nextC = c;

  if (r - 1 >= 0 && grid[r-1][c]) 
    nextR = r - 1;
  else if (r + 1 < gridSize && grid[r+1][c])
    nextR = r + 1;
  else if (c - 1 >= 0 && grid[r][c-1])
    nextC = c - 1;
  else if (c + 1 < gridColSize[r] && grid[r][c+1])
    nextC = c + 1;
  else
    return 1;

  reaches = numReachables(nextR, nextC, grid, gridSize, gridColSize);
  grid[r][c] = 1;
  if (reaches < islandSize - 1) 
    return 1;
  return 2;
}


/* scans the given grid and rules out the case that the given grid is
   disconnected initially.
 */
int scan(int** grid, int gridSize, int* gridColSize) {
  int islandSize = -1;
  int numLands = 0;
  int days = 3;
  
  for (int i = 0; i < gridSize; i++)
    for (int j = 0; j < gridColSize[i]; j++) {
      if (!grid[i][j])
	continue;
      
      if (!numLands) {
	islandSize = numReachables(i, j, grid, gridSize, gridColSize);
	numLands = 1;
      } else
	numLands++;
      
      if (days > 1) {
	printf("try %d %d", i, j);
	int newDays = tryDisconnect(i, j, grid, gridSize, gridColSize, islandSize);
	printf("--> %d\n", newDays);
	if (newDays < days)
	  days = newDays;
      }
    }
  if (numLands > islandSize)
    return 0;
  return days;
}


int minDays(int** grid, int gridSize, int* gridColSize){
  maxCols = 0;
  for (int i = 0; i < gridSize; i++)
    if (maxCols < gridColSize[i])
      maxCols = gridColSize[i];
  numRows = gridSize;
  return scan(grid, gridSize, gridColSize);
}

int main() {

  int g[4][5] = {{1,1,0,1,1},
		 {1,1,1,1,1},
		 {1,1,1,1,1},
		 {1,1,0,1,1}};  
  int gridSize = 4;
  int gridColSize[4] = {5,5,5,5};

  int **grid;
  int days;

  grid = (int**)malloc(sizeof(int*) * gridSize);
  for (int i = 0; i < gridSize; i++) {
    grid[i] = &g[i][0];
  }
  
  days = minDays(grid, gridSize, gridColSize);
  printf("%d\n", days);
  free(grid);
  return 0;
}
