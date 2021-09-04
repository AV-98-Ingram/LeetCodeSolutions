import java.util.LinkedList;
import java.util.List;

class Solution {
	char[] t2 = { 'a', 'b', 'c' };
	char[] t3 = { 'd', 'e', 'f' };
	char[] t4 = { 'g', 'h', 'i' };
	char[] t5 = { 'j', 'k', 'l' };
	char[] t6 = { 'm', 'n', 'o' };
	char[] t7 = { 'p', 'q', 'r', 's' };
	char[] t8 = { 't', 'u', 'v' };
	char[] t9 = { 'w', 'x', 'y', 'z' };

	char[][] table = new char[8][];

	Solution() {
		table[0] = t2;
		table[1] = t3;
		table[2] = t4;
		table[3] = t5;
		table[4] = t6;
		table[5] = t7;
		table[6] = t8;
		table[7] = t9;
	}

	public List<String> letterCombinations(String digits) {
		int[] nums = new int[digits.length()];

		for (int i = 0; i < nums.length; i++)
			nums[i] = Integer.valueOf("" + digits.charAt(i));

		List<String> init = new LinkedList<>();

		if (nums.length == 0)
			return init;
		init.add("");
		return elaborate(init, nums, 0);
	}

	private List<String> elaborate(List<String> in, int digits[], int idx) {
		if (idx == digits.length)
			return in;

		List<String> result = new LinkedList<>();

		for (String s : in) {
			for (char c : table[digits[idx] - 2]) {
				result.add(s + c);
			}
		}
		return elaborate(result, digits, idx + 1);
	}

	public static void main(String[] args) {
		System.out.print(new Solution().letterCombinations(""));
	}
}
