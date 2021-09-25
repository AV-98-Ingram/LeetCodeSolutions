import java.util.BitSet;

class Solution {

	public String getPermutation(int n, int k) {
		if (n == 1)
			return "1";

		int[] fact = new int[n - 1];
		BitSet set = new BitSet(n);

		set.clear();
		fact[0] = 1;
		for (int i = 1; i < n - 1; i++)
			fact[i] = fact[i - 1] * (i + 1);

		String ret = "";

		k = k - 1; // make it 0-based k
		for (int i = 0; i < n - 1; i++) {
			int fac = fact[n - 2 - i]; // fac loops from (n-1)! to 1!
			int num = k / fac;

			// find the num-th (0-based) unused number
			int ch = -1;
			for (int j = 0; j < num; j++)
				ch = set.nextClearBit(ch + 1);
			ch = set.nextClearBit(ch + 1);

			ret += ch + 1;
			set.set(ch);
			k = k % fac;
		}
		ret += set.nextClearBit(0) + 1;
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().getPermutation(5, 20));
	}
}
