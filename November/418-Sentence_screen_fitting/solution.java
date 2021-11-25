class Solution {
	/*
	 * This simple solution hits time limit:
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
		}
		return times;
	}

	public static void main(String[] args) {
		System.out.println(new Solution()
				.wordsTyping(new String[] { "hello", "world" }, 2, 8));
	}
}
