class Solution {
	/*
	 * When the algorithms reaches the initial state at the second, i.e.,
	 * (colPos = 0 and wordPos = 0), we could skip rows that repeats completely.
	 */
	public int wordsTyping(String[] sentence, int rows, int cols) {
		int wordPos = 0;
		int times = 0;

		for (int i = 0; i < rows; i++) {
			int colPos = 0; // where to put next word

			while (colPos + sentence[wordPos].length() <= cols) {
				colPos += sentence[wordPos].length() + 1; // next colPos
				wordPos++; // next word index
				if (wordPos >= sentence.length) {
					times++;
					wordPos = 0;
				}
			}
			if (wordPos == 0 && times > 0) {
				// reaches the initial state again, we can then skip n * (i+1)
				// lines for the maximum n such that n * (i+1) < rows:
				int n = rows / (i + 1);
				times = n * times;
				// minus one for the coming loop incrementor i++:
				i = n * (i + 1) - 1;
			}
		}
		return times;
	}

	public static void main(String[] args) {
		System.out.println(new Solution()
				.wordsTyping(new String[] { "a", "b", "cde" }, 3, 3));
	}
}
