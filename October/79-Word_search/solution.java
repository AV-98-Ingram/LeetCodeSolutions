class Solution {

    private Map<Character, List<int[]>> table = new HashMap<>();
    
    public boolean exist(char[][] board, String word) {
	int nr = board.length;
	int nc = board[0].length;
	
        visits = new boolean[nr][nc];
	for (int[] row : visits)
	    Arrays.fill(row, false);
	for (int i = 0; i < nr; i++)
	    for (int j = 0; j < nc; j++) {
		List<int[]> coords = table.computeIfAbsent(board[i][j], (k)->(new LinkedList<>()));

		coords.add(new int[]{i, j});
	    }

	List<int[]> coords = table.get(word.charAt(0));

	if (coords == null)
	    return false;
	for (int[] coord : coords) {
	    visits[coord[0]][coord[1]] = true;
	    if (f(word, 1, visits, coord[0], coord[1]))
		return true;
	    visits[coord[0]][coord[1]] = false;
	}
	return false;
    }

    private boolean f(String word, int wordPos, boolean[][] visits, int r, int c) {
	if (wordPos == word.length())
	    return true;
	
	char c = word.charAt(wordPos);
	List<int[]> coords = table.get(c);

	if (coords == null)
	    return false;
	for (int[] coord : coords) {
	    if (visits[coord[0]][corrd[1]])
		continue;
	    if (isAdjacent(r, c, coord[0], corrd[1])) {
		visits[coord[0]][corrd[1]] = true;
		if (f(word, wordPos + 1, visits, coord[0], coord[1]))
		    return true;
		visits[coord[0]][corrd[1]] = false;
	    }
	}
	return false;
    }

    private boolean isAdjacent(int x, int y, int a, int b) {
	if (x == a) {
	    return Maths.abs(y - b) == 1;
	}
	return y == b && Maths.abs(x - a) == 1;
    }
}
