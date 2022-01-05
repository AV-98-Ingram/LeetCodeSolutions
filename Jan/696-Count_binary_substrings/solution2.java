class Solution {
	public int countBinarySubstrings(String s) {
		final int len = s.length();
		int result = 0;
		int i = 0, nxt_i = 0;
		int balance = 0, flips = 0;

		for (int j = 0; j < len; j++) {
			char c = s.charAt(j);

			if (j > 0 && c != s.charAt(j - 1)) {
				if (++flips > 1) {
					int n = (j - i - Math.abs(balance)) / 2;

					i = nxt_i;
					result += n;
					balance = (j - nxt_i) * -(c == '0' ? -1 : 1);
					flips = 1;
				}
				nxt_i = j;
			}
			balance += (c == '0' ? -1 : 1);
		}
		result += (len - i - Math.abs(balance)) / 2;
		return result;
	}

	public static void main(String[] args) {
		new Solution().countBinarySubstrings("1000110");
	}
}
