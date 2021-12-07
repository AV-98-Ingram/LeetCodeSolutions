class Solution {
	public boolean canTransform(String start, String end) {
		final int len = start.length();
		int p = 0;
		int q = 0;
		char[] s = start.toCharArray();
		char[] e = end.toCharArray();

		// left move first:
		while (p < len || q < len) {
			if ((p < len && s[p] != 'L') && (q < len && e[q] != 'L')) {
				p++;
				q++;
				continue;
			} else if (p < len && s[p] != 'L') {
				p++;
				continue;
			} else if (q < len && e[q] != 'L') {
				q++;
				continue;
			}
			if (p >= len || q >= len || p < q)
				return false;

			int next = Math.min(p, q);

			while (p > q)
				if (s[p - 1] == 'X') {
					// transform "XL" to "LX":
					s[p - 1] = 'L';
					s[p] = 'X';
					p--;
				} else
					return false;
			p = q = next + 1;
		}
		// now move right:
		p = len - 1;
		q = len - 1;
		while (p >= 0 || q >= 0) {
			if ((p >= 0 && s[p] != 'R') && (q >= 0 && e[q] != 'R')) {
				p--;
				q--;
				continue;
			} else if (p >= 0 && s[p] != 'R') {
				p--;
				continue;
			} else if (q >= 0 && e[q] != 'R') {
				q--;
				continue;
			}
			if (p < 0 || q < 0 || p > q)
				return false;

			int next = Math.max(p, q);

			while (p < q) {
				if (s[p + 1] == 'X') {
					// transform "RX" to "XR":
					s[p + 1] = 'R';
					s[p] = 'X';
					p++;
				} else
					return false;
			}
			p = q = next - 1;
		}
		return true;
	}

	public static void main(String[] args) {
		new Solution().canTransform("XLXRRXXRXX", "LXXXXXXRRR");
	}
}
