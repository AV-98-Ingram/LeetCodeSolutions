import java.util.LinkedList;
import java.util.List;

class Solution {
	public String minWindow(String s1, String s2) {
		List<Integer> starters = new LinkedList<>();
		char[] s1arr = s1.toCharArray();

		for (int i = 0; i < s1arr.length; i++)
			if (s1arr[i] == s2.charAt(0))
				starters.add(i);

		String min = "";

		for (int i : starters) {
			String str = match(i, s1, s2);

			if (min.length() == 0
					|| (str.length() > 0 && str.length() < min.length()))
				min = str;
		}
		return min;
	}

	private String match(int start, String s1, String s2) {
		final int len1 = s1.length();
		final int len2 = s2.length();
		int pos = 1;
		int i;

		for (i = start + 1; i < len1 && pos < len2; i++) {
			if (s1.charAt(i) == s2.charAt(pos))
				pos++;
		}
		if (pos >= len2)
			return s1.substring(start, i);
		return "";
	}

	public static void main(String[] args) {
		new Solution().minWindow("cnhczmccqouqadqtmjjzl", "mm");
	}
}

