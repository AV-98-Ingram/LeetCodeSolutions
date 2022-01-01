class Solution {
    int nr, nc;
    
    public int shortestPathBinaryMatrix(int[][] grid) {	
	LinkedList<int[]> que = new LinkedList<>();
	LinkedList<int[]> nxt = new LinkedList<>();
	Set<Integer> visited = new HashSet<>();
	int step = 1;

	if (grid[0][0] != 0)
	    return -1;
	nr = grid.length;
	nc = grid[0].length;
	que.add(new int[] {0,0});
	while (!que.isEmpty()) {
	    while (!que.isEmpty()) {
		int[] cell = que.removeFirst();

		if (cell[0] == nr-1 && cell[1] == nc-1)
		    return step;
		if (!visited.add(cell[0] * nc + cell[1]))
		    continue;		
		for (int[] next : nexts(cell[0], cell[1], grid, nr, nc))
		    nxt.add(next);
	    }
	    step++;
	    
	    LinkedList<int[]> tmp = que;
	    
	    que = nxt;
	    nxt = tmp;
	}
	return -1;
    }

    private List<int[]> nexts(int r, int c, int[][] grid, int nr, int nc) {
	LinkedList<int[]> ret = new LinkedList<>();
	
	if (r > 0) {
	    ret.add(new int[]{r - 1, c});
	    if (c > 0)
		ret.add(new int[]{r - 1, c - 1});
	    if (c < nc - 1)
		ret.add(new int[]{r - 1, c + 1});
	}
	if (c > 0) {
	    ret.add(new int[]{r, c - 1});
	    if (r < nr - 1)
		ret.add(new int[]{r + 1, c - 1});		
	}
	if (r < nr - 1) {
	    ret.add(new int[]{r + 1, c});
	    if (c < nc - 1)
		ret.add(new int[]{r + 1, c + 1});
	}
	if (c < nc - 1)
	    ret.add(new int[]{r, c + 1});

	int size = ret.size();

	for (int i = 0; i < size; i++) {
	    int cell[] = ret.removeFirst();

	    if (grid[cell[0]][cell[1]] == 0)
		ret.addLast(cell);	    
	}
	return ret;
    }
}
