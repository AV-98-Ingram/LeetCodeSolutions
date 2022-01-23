import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    class Cell {

	int r, c, val, dist;

	Cell(int r, int c, int val) {
	    this.r = r;
	    this.c = c;
	    this.val = val;
	}

	public boolean equals(Object o) {
	    if (o instanceof Cell) {
		Cell other = (Cell) o;

		return other.r == r && other.c == c;
	    }
	    return false;
	}

	public int hashCode() {
	    return (r << 6) + c;
	}

	public String toString() {
	    return "(" + r + ", " + c + ")";
	}
    }

    static int[][] directs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    static int NR, NC;

    // brutal force BFS
    public int cutOffTree(List<List<Integer>> forest) {
	NR = forest.size();
	NC = forest.get(0).size();

	Cell[][] mat = new Cell[NR][NC];
	int r = 0;
	PriorityQueue<Cell> trees = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

	for (List<Integer> row : forest) {
	    int c = 0;

	    for (int height : row) {
		mat[r][c] = new Cell(r, c, height);
		if (height > 1)
		    trees.add(mat[r][c]);
		c++;
	    }
	    r++;
	}

	int result = 0;
	Cell start = mat[0][0];

	while (!trees.isEmpty()) {
	    Cell tree = trees.poll();

	    int dist = bfs(start, tree, mat);

	    if (dist == -1)
		return -1;
	    result += dist;
	    start = tree;
	}
	return result;
    }

    private int bfs(Cell start, Cell end, Cell[][] mat) {
	LinkedList<Cell> que = new LinkedList<>();
	byte[][] visited = new byte[NR][NC];

	start.dist = 0;
	que.add(start);
	while (!que.isEmpty()) {
	    Cell curr = que.removeFirst();

	    if (curr.equals(end))
		return curr.dist;
	    if (visited[curr.r][curr.c] == 1)
		continue;
	    visited[curr.r][curr.c] = 1;
	    for (int[] direct : directs) {
		int nxtR = curr.r + direct[0];
		int nxtC = curr.c + direct[1];

		if (nxtR >= 0 && nxtR < NR && nxtC >= 0 && nxtC < NC) {
		    Cell next = mat[nxtR][nxtC];

		    if (next.val == 0)
			continue;
		    next.dist = curr.dist + 1;
		    que.add(next);
		}
	    }
	}
	return -1;
    }
}
