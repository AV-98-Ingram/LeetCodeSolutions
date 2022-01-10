class Solution {
    static int[][] directions = new int[][] {
	{0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };
    
    public int orangesRotting(int[][] grid) {
	final int nr = grid.length;
	final int nc = grid[0].length;
	LinkedList<int[]> que = new LinkedList<>();
	int time = 0;
	int nfreshes = 0;
	
	for (int i = 0; i < nr; i++)
	    for (int j = 0; j < nc; j++) {
		if (grid[i][j] == 2) 
		    que.add(new int[] {i, j, 0});
		if (grid[i][j] == 1) 
		    nfreshes++;
	    }
	if (nfreshes == 0)
	    return 0;
	while (!que.isEmpty()) {
	    int[] rot = que.removeFirst();
	    
	    time = Math.max(time, rot[2]);
	    if (grid[rot[0]][rot[1]] == 1) {
		grid[rot[0]][rot[1]] = 2;
		nfreshes--;
		if (nfreshes == 0)
		    return time;
	    }
	    for (int[]  direct : directs) {
		int r = rot[0] + direct[0];
		int c = rot[1] + direct[1];
		
		if (0 <= r && r < nr && 0 <= c && c < nc)
		    if (grid[r][c] == 1)
			que.add(new int[]{r, c, rot[2] + 1});
	    }
	}
	return -1;
    }
}
