import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Solution {

	class LetterLog {
		String id;
		String content;
		String full;

		LetterLog(String log) {
			String[] words = log.split(" ");

			id = words[0];

			StringBuffer sb = new StringBuffer();

			for (int i = 1; i < words.length; i++)
				sb.append(words[i]).append(" ");
			content = sb.toString();
			full = log;
		}
	}

	public String[] reorderLogFiles(String[] logs) {
		List<LetterLog> letters = new ArrayList<>();
		List<String> digits = new LinkedList<>();

		for (String log : logs) {
			if (isLetter(log))
				letters.add(new LetterLog(log));
			else
				digits.add(log);
		}

		Collections.sort(letters, (a, b) -> (compare(a, b)));

		String[] results = new String[letters.size() + digits.size()];
		int i = 0;

		for (LetterLog letter : letters)
			results[i++] = letter.full;
		for (String digLog : digits)
			results[i++] = digLog;
		return results;
	}

	private int compare(LetterLog a, LetterLog b) {
		int comp = a.content.compareTo(b.content);

		if (comp != 0)
			return comp;
		return a.id.compareTo(b.id);
	}

	private boolean isLetter(String log) {
		char c = log.split(" ")[1].charAt(0);

		return 'a' <= c && c <= 'z';
	}
}
