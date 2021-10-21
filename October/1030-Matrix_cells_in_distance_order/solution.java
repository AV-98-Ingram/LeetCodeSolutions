class Solution {

	private int x, y, xb, yb;

	public int[][] allCellsDistOrder(int rows, int cols, int rCenter,
			int cCenter) {
		x = rCenter;
		y = cCenter;
		xb = rows;
		yb = cols;

		int[][] out = new int[rows * cols][2];

		f(0, out, 0);
		return out;
	}

	private int fill(int x, int y, int[][] out, int outp) {
		if (x < 0 || x >= xb || y < 0 || y >= yb)
			return outp;
		out[outp][0] = x;
		out[outp++][1] = y;
		return outp;
	}

	private int f(int dist, int[][] out, int outp) {
		int oldOutp = outp;

		for (int i = 0; i <= dist; i++) {
			outp = fill(x + i, y + (dist - i), out, outp);
			if (i != 0)
				outp = fill(x - i, y + (dist - i), out, outp);
			if ((dist - i) != 0) {
				outp = fill(x + i, y - (dist - i), out, outp);
				if (i != 0)
					outp = fill(x - i, y - (dist - i), out, outp);
			}
		}
		if (outp != oldOutp)
			return f(dist + 1, out, outp);
		return outp;
	}

	public static void main(String[] args) {
		new Solution().allCellsDistOrder(2, 3, 1, 2);
	}
}
