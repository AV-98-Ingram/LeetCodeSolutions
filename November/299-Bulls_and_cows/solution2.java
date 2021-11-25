class Solution {
	public String getHint(String secret, String guess) {
		char[] s = secret.toCharArray();
		char[] g = guess.toCharArray();
		final int len = s.length;
		int bulls = 0;
		int cows = 0;
		int[] cowTable = new int[10];

		for (int i = 0; i < len; i++) {
			if (s[i] == g[i])
				bulls++;
			else {
				if (cowTable[s[i] - '0']++ < 0)
					// there were s[i] - '0' being saw in guess:
					cows++;
				if (cowTable[g[i] - '0']-- > 0)
					// g[i] - '0' in guess matches the digit previously being
					// saw in secret:
					cows++;
			}
		}
		return bulls + "A" + cows + "B";
	}
}

