class Solution {

	public int findKthNumber(int m, int n, int k) {
		int l = m > n ? m : n;
		int s = m > n ? n : m;

		int lo = 1, hi = l * s;

		while (lo < hi) {
			int mid = (hi - lo) / 2 + lo;
			int pos = pos(mid, l, s);

			// if (pos == k) return mid;
			// This is wrong. Why?
			/*
			 * Because 'mid' is not necessarily in the table, for example 7 is
			 * not in a 3 X 3 table but its 'pos' can be computed.
			 * 
			 * But why the computed 'lo' must be in table? First, we have a loop
			 * invariant: Nothing lower than 'lo' is at a position that is equal
			 * to k. Now suppose 'lo' is at position k but is not in the table.
			 * Then there must be a number x in the table such that x shares the
			 * position with 'lo'. Consequently, 'lo' is not the lowest number
			 * whose position >= k. It contradicts the loop invariant.
			 */
			if (pos >= k)
				hi = mid;
			else
				lo = mid + 1;
		}
		return lo;
	}

	/**
	 * @return the position of x in the table. If x is not in the table,
	 *         returning the position of y such that y is in the table,
	 *         <code>y < x </code>, and there is NO such <code>y'</code> that
	 *         <code>y < y' < x</code>.
	 */
	private int pos(int x, int l, int s) {
		int pos = 0;

		for (int i = 1; i <= s; i++) {
			pos += Math.min(x / i, l);
		}
		return pos;
	}
}
