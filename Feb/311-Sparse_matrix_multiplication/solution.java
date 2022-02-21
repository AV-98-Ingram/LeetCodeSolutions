import java.util.ArrayList;

class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
	int nrows1 = mat1.length;
	int ncols1 = mat1[0].length;
	int ncols2 = mat2[0].length;
	int[][] result = new int[nrows1][ncols2];
	ArrayList<ArrayList<Integer>> smat1 = new ArrayList<>(); // smat1[i]: non-zero elements index in row i of mat1
	ArrayList<ArrayList<Integer>> smat2 = new ArrayList<>(); // smat2[i]: non-zero elements index in row i of mat2

	for (int r = 0; r < nrows1; r++) {
	    ArrayList<Integer> l = new ArrayList<>();

	    for (int c = 0; c < ncols1; c++)
		if (mat1[r][c] != 0)
		    l.add(c);
	    smat1.add(l);
	}
	for (int r = 0; r < ncols1; r++) {
	    ArrayList<Integer> l = new ArrayList<>();

	    for (int c = 0; c < ncols2; c++)
		if (mat2[r][c] != 0)
		    l.add(c);
	    smat2.add(l);
	}
	for (int r = 0; r < nrows1; r++) {
	    for (int col : smat1.get(r)) {
		// mat1[r][c] * mat2[c][k] --contribute--> result[r][k]
		for (int col2 : smat2.get(col))
		    result[r][col2] += mat1[r][col] * mat2[col][col2];
	    }
	}
	return result;
    }
}
