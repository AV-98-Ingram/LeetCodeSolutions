import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

class Excel {

	class Formula {
		BiFunction<int[][], Formula[][], Integer> fun;
	}

	int mat[][];

	Formula[][] sums;

	public Excel(int height, char width) {
		mat = new int[height][width - 'A' + 1];
		sums = new Formula[height][width - 'A' + 1];
		for (int i = 0; i < height; i++)
			for (char c = 'A'; c <= width; c++) {
				mat[i][c - 'A'] = 0;
				sums[i][c - 'A'] = null;
			}
	}

	public void set(int row, char column, int val) {
		mat[row - 1][column - 'A'] = val;
		sums[row - 1][column - 'A'] = null;
	}

	public int get(int row, char column) {
		if (sums[row - 1][column - 'A'] != null)
			return sums[row - 1][column - 'A'].fun.apply(mat, sums);
		else
			return mat[row - 1][column - 'A'];
	}

	public int sum(int row, char column, String[] numbers) {
		sums[row - 1][column - 'A'] = parse(numbers);
		return sums[row - 1][column - 'A'].fun.apply(mat, sums);
	}

	private Formula parse(String[] numbers) {
		final List<int[][]> ranges = new LinkedList<>();

		for (String s : numbers)
			ranges.add(parseWorker(s));

		Formula formula = new Formula();

		formula.fun = (mat, sums) -> {
			int result = 0;
			for (int[][] range : ranges) {
				for (int i = range[0][0]; i <= range[0][1]; i++)
					for (int j = range[1][0]; j <= range[1][1]; j++) {
						if (sums[i - 1][j] != null)
							result += sums[i - 1][j].fun.apply(mat, sums);
						else
							result += mat[i - 1][j];
					}
			}
			return result;
		};
		return formula;
	}

	private int[][] parseWorker(String number) {
		// row: {from, to}
		// col: {from, to}
		int[][] range = new int[2][2];
		String[] colRows = number.split(":");

		range[0][0] = 0; // row start
		for (int j = 1; j < colRows[0].length(); j++)
			range[0][0] = range[0][0] * 10 + (colRows[0].charAt(j) - '0');
		range[1][0] = colRows[0].charAt(0) - 'A'; // col start

		int to = 0;

		if (colRows.length > 1)
			to = 1;
		range[0][1] = 0; // row end
		for (int j = 1; j < colRows[to].length(); j++)
			range[0][1] = range[0][1] * 10 + (colRows[to].charAt(j) - '0');
		range[1][1] = colRows[to].charAt(0) - 'A'; // col end
		return range;
	}

	public static void main(String[] args) {
		Excel excel = new Excel(3, 'C');

		System.out.println(excel.sum(1, 'A', new String[] { "A2" }));
		excel.set(2, 'A', 1);
		System.out.println(excel.sum(3, 'A', new String[] { "A1" }));
		System.out.println(excel.get(1, 'A'));
	}
}
