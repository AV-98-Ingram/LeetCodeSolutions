import java.util.LinkedList;
import java.util.List;

class Solution {
	private class ParenString {
		String s;
		int openParens;

		ParenString() {
			s = "";
			openParens = 0;
		}

		ParenString(String s, int openParens) {
			this.s = s;
			this.openParens = openParens;
		}

		ParenString closeParen() {
			return new ParenString(s + ")", openParens - 1);
		}

		ParenString openParen() {
			return new ParenString(s + "(", openParens + 1);
		}

		@Override
		public String toString() {
			return s;
		}
	}

	public List<String> generateParenthesis(int n) {
		List<String> ret = new LinkedList<>();

		gp(new ParenString(), n, ret);
		return ret;
	}

	private void gp(ParenString input, int n, List<String> out) {
		if (n == 0) {
			while (input.openParens > 0) {
				// close until no open paren
				input = input.closeParen();
			}
			out.add(input.toString());
			return;
		}
		gp(input.openParen(), n - 1, out);
		if (input.openParens > 0)
			gp(input.closeParen(), n, out);
	}
}
