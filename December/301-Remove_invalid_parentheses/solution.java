import java.util.LinkedList;
import java.util.List;

class Solution {
	/*
	 * Idea: The minimum number of open parenthesis and close parenthesis shall be
	 * removed can be counted, respectively, exactly during a scan. Then with these
	 * two upper bounds, we explore all possible ways of removal using backtracking.
	 * 
	 * The backtracking algorithm is recursive. Each recursion, it deals with ONLY
	 * one of the three cases:
	 * 
	 * 1. a maximal consecutive sequence of letters
	 * 
	 * 2. a maximal consecutive sequence of open parens
	 * 
	 * 3. a maximal consecutive sequence of close parens
	 * 
	 * For case 1, we simply keep all those letters. For case 2 and 3, we can choose
	 * to delete [0, n] parens, where n is the number of the kind of parenthese that
	 * shall be but haven't been removed at that state.
	 * 
	 * In addition, we also keep track of the number of open parentheses in the
	 * exploring string so that we can determine if an exploring string is valid.
	 */
	public List<String> removeInvalidParentheses(String s) {
		final int len = s.length();
		LinkedList<Integer> stack = new LinkedList<>();
		int deleteCloses = 0, deleteOpens = 0;

		for (int i = 0; i < len; i++) {
			if (stack.isEmpty()) {
				if (s.charAt(i) == ')')
					deleteCloses++;
				else if (s.charAt(i) == '(')
					stack.addFirst(i);
			} else {
				if (s.charAt(i) == ')')
					stack.removeFirst();
				else if (s.charAt(i) == '(')
					stack.addFirst(i);
			}
		}
		deleteOpens = stack.size();
		return f(s, 0, 0, deleteCloses, deleteOpens);
	}

	private List<String> f(String s, int start, int numOpens, int closes2del, int opens2del) {
		if (closes2del < 0 || opens2del < 0 || numOpens < 0)
			return null; // invalid case

		List<String> result = new LinkedList<>();
		final int end = s.length();

		if (start >= end)
			if (closes2del == 0 && opens2del == 0 && numOpens == 0) {
				result.add("");
				return result;
			} else
				return null;

		int i = start;
		StringBuilder sb = new StringBuilder();

		// simply add consecutive letters to "sb":
		while (i < end && s.charAt(i) != ')' && s.charAt(i) != '(') {
			sb.append(s.charAt(i));
			i++;
		}
		if (i == end) {
			if (closes2del == 0 && opens2del == 0 && numOpens == 0) {
				result.add(sb.toString());
				return result;
			} else
				return null;
		}

		// process "(" or ")" in a generic way:
		char paren = s.charAt(i);
		int toDelete = paren == '(' ? opens2del : closes2del;
		int numParens = 0;

		while (i < end && s.charAt(i) == paren) {
			numParens++;
			i++;
		}

		// if there are more parentheses than what needs to be deleted, we directly add
		// them to "sb":
		int keepParen = numParens > toDelete ? numParens - toDelete : 0;

		for (int j = 0; j < keepParen; j++)
			sb.append(paren);
		// update the number of open parens in the exploring string:
		numOpens = paren == ')' ? numOpens - keepParen : numOpens + keepParen;
		// numParens: the number of parens that can be tried to delete
		numParens -= keepParen;
		// try to delete [0, numParen] parenthesis
		for (int j = 0; j <= numParens; j++) { // j is the number of non-deleted parenthesis
			List<String> suffixes = f(s, i, paren == '(' ? numOpens + j : numOpens - j,
					paren == '(' ? closes2del : closes2del - (numParens - j),
					paren == '(' ? opens2del - (numParens - j) : opens2del);

			if (suffixes != null) {
				String ss = sb.toString();

				for (String sfx : suffixes) {
					result.add(ss + sfx);
				}
			}
			sb.append(paren);
		}
		return result;
	}

	public static void main(String args[]) {
		new Solution().removeInvalidParentheses(")(f");
	}
}
