import java.util.stream.Stream;

// parallel solution:
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
	final int[] arr = new int[length];

	if (length >= 1000)
	    Stream.iterate(0, (i) -> (i < length), (i) -> (i + 1)).
		parallel().forEach((i) -> f(arr, i, updates));
	else
	    Stream.iterate(0, (i) -> (i < length), (i) -> (i + 1)).
		forEach((i) -> f(arr, i, updates));
	return arr;
    }

    static void f(final int[] arr, final int col, final int[][] updates) {
	int incr = 0;

	for (int[] up : updates) {
	    if (up[0] <= col && col <= up[1])
		incr += up[2];
	}
	arr[col] = incr;
    }
}
