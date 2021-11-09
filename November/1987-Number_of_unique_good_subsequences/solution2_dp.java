class Solution {

	public int numberOfUniqueGoodSubsequences(String binary) {
		final int len = binary.length();
		int bfLastOne = 0, // before last "one"
				bfLastZero = 0, // before last "zero"
				last = 0;
		int i;

		for (i = 0; i < len; i++)
			if (binary.charAt(i) == '1')
				break;

		int start = i;

		last = i < len ? 1 : 0;
		i++;
		for (; i < len; i++) {
			int next;

			if (binary.charAt(i) == '1') {
				next = last + (last + 1000000007 - bfLastOne) % 1000000007;
				bfLastOne = last;
			} else {
				next = last + (last + 1000000007 - bfLastZero) % 1000000007;
				bfLastZero = last;
			}
			last = next;
			last = last % 1000000007;
		}
		if (bfLastZero > 0 || start > 0)
			last += 1; // adding '0'
		return last;
	}

	public static void main(String[] args) {
		new Solution().numberOfUniqueGoodSubsequences("0");
	}
}
