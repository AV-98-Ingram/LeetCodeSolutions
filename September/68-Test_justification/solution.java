import java.util.LinkedList;
import java.util.List;

class Solution {
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> result = new LinkedList<>();
		int start = 0;

		while (start < words.length) {
			int end = endIndexForNextLine(words, maxWidth, start);

			if (end == words.length)
				result.add(printLastLine(words, maxWidth, start));
			else
				result.add(printLine(words, maxWidth, start, end));
			start = end;
		}
		return result;
	}

	private int endIndexForNextLine(String[] words, int maxWidth, int start) {
		int leastLen = words[start].length();
		int end = start + 1;

		while (end < words.length) {
			int wordLen = words[end].length();
			leastLen += 1 + wordLen;
			if (leastLen > maxWidth)
				return end;
			end++;
		}
		return end;
	}

	private String printLastLine(String[] words, int maxWidth, int start) {
		String line = words[start];

		for (int i = start + 1; i < words.length; i++)
			line += ' ' + words[i];
		for (int i = line.length(); i < maxWidth; i++)
			line += ' ';
		return line;
	}

	private String printLine(String[] words, int maxWidth, int start, int end) {
		int wordsLen = 0;

		for (int i = start; i < end; i++)
			wordsLen += words[i].length();

		int remnant = maxWidth - wordsLen;
		String line = words[start];

		if (end - start == 1) {
			for (int i = 0; i < remnant; i++)
				line += ' ';
			return line;
		}

		int nspaces = remnant / (end - start - 1);
		int nextras = remnant % (end - start - 1);
		String theSpace = "";

		for (int i = 0; i < nspaces; i++)
			theSpace += ' ';
		for (int i = start + 1; i < end; i++) {
			if (nextras > 0) {
				line += ' ';
				nextras--;
			}
			line += theSpace + words[i];
		}
		return line;
	}
}
