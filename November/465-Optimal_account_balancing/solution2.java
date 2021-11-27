import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * simply try out all possible matches
 * <p>
 * Time complexity O(n!)
 * </p>
 */
class Solution {
	public int minTransfers(int[][] transactions) {
		int[] guys = new int[21];

		Arrays.fill(guys, 0);
		for (int[] trans : transactions) {
			guys[trans[0]] -= trans[2];
			guys[trans[1]] += trans[2];
		}

		ArrayList<Integer> pos = new ArrayList<>();
		ArrayList<Integer> neg = new ArrayList<>();

		for (int i = 0; i <= 20; i++)
			if (guys[i] > 0)
				pos.add(i);
			else if (guys[i] < 0)
				neg.add(i);
		Collections.sort(pos);
		Collections.sort(neg);
		return match(pos, 0, neg, guys, 0);
	}

	private int match(final ArrayList<Integer> pos, int start,
			final ArrayList<Integer> negs, final int[] guys, int trans) {
		if (start >= pos.size())
			return trans;

		int min = pos.size() + negs.size() - 1; // initial value is the maximal
												// trans

		for (int neg : negs) {
			if (guys[neg] == 0)
				continue;

			int oldPos = guys[pos.get(start)];
			int oldNeg = guys[neg];
			int balance = oldPos + oldNeg;
			int nextStart = start + 1;

			guys[pos.get(start)] = 0;
			guys[neg] = 0;
			if (balance > 0) {
				guys[pos.get(start)] = balance;
				nextStart--; // having balance remaining
			} else if (balance < 0)
				guys[neg] = balance;

			int totalTrans = match(pos, nextStart, negs, guys, trans + 1);

			min = Math.min(totalTrans, min);
			// change guys back
			guys[pos.get(start)] = oldPos;
			guys[neg] = oldNeg;
		}
		return min;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().minTransfers(
				new int[][] { { 1, 0, 18 }, { 2, 1, 9 }, { 4, 3, 11 },
						{ 5, 4, 10 }, { 5, 6, 7 }, { 7, 6, 5 }, { 8, 7, 3 } }));
	}
}
