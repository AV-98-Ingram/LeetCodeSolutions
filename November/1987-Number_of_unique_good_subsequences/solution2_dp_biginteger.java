import java.math.BigInteger;

class Solution {

	public int numberOfUniqueGoodSubsequences(String binary) {
		final int len = binary.length();
		BigInteger bfLastOne = null, // before last "one"
				bfLastZero = null, // before last "zero"
				last = null;
		int i;

		for (i = 0; i < len; i++)
			if (binary.charAt(i) == '1') {
				bfLastOne = BigInteger.ZERO;
				break;
			}

		int start = i;

		last = i < len ? BigInteger.ONE : BigInteger.ZERO;
		i++;
		for (; i < len; i++) {
			BigInteger next = last.add(last);

			if (binary.charAt(i) == '1') {
				if (bfLastOne != null)
					next = next.subtract(bfLastOne);
				bfLastOne = last;
			} else {
				if (bfLastZero != null)
					next = next.subtract(bfLastZero);
				bfLastZero = last;
			}
			last = next;
		}
		if (bfLastZero != null || start > 0)
			last = last.add(BigInteger.ONE); // adding '0'
		return last.mod(BigInteger.valueOf(1000000000 + 7)).intValue();
	}

	public static void main(String[] args) {
		new Solution().numberOfUniqueGoodSubsequences("0");
	}
}
