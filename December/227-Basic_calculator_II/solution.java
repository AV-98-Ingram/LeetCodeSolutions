class Solution {

	public int calculate(String s) {
		s = removeSpace(s);

		final int len = s.length();
		int pos = 0;
		int operand1;
		int nextPos = nextPlusMinus(s, pos);

		operand1 = calcMultDiv(s, pos, nextPos);
		pos = nextPos;
		while (pos < len) {
			char op = s.charAt(pos++);
			int operand2;

			nextPos = nextPlusMinus(s, pos);
			operand2 = calcMultDiv(s, pos, nextPos);
			operand1 = op == '+' ? operand1 + operand2 : operand1 - operand2;
			pos = nextPos;
		}
		return operand1;
	}

	private int calcMultDiv(String s, int start, int end) {
		int[] numRet = nextNum(s, start);
		int pos = numRet[0];
		int operand1 = numRet[1];

		while (pos < end) {
			char op = s.charAt(pos++);
			int operand2;

			numRet = nextNum(s, pos);
			pos = numRet[0];
			operand2 = numRet[1];
			operand1 = op == '*' ? operand1 * operand2 : operand1 / operand2;
		}
		return operand1;
	}

	private int nextPlusMinus(String s, int pos) {
		final int len = s.length();
		char c;

		do {
			c = s.charAt(pos);
			if (c == '+' || c == '-')
				break;
			pos++;
		} while (pos < len);
		return pos;
	}

	private int[] nextNum(String s, int pos) {
		char c;
		int i = 0;
		final int len = s.length();

		do {
			c = s.charAt(pos);
			if (c < '0' || c > '9')
				break;
			i *= 10;
			i += c - '0';
			pos++;
		} while (pos < len);
		return new int[] { pos, i };
	}

	private String removeSpace(String s) {
		StringBuilder sb = new StringBuilder();

		for (char c : s.toCharArray()) {
			if (c != ' ')
				sb.append(c);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(new Solution().calculate("3+2*2"));
	}
}
