import java.util.LinkedList;
import java.util.List;

class Solution {
	public String countAndSay(int n) {
		List<Character> ret = say(n, new LinkedList<>());
		StringBuffer sb = new StringBuffer();

		for (Character c : ret)
			sb.append(c);
		return sb.toString();
	}

	// requires: 1 <= n <= 30
	private List<Character> say(int n, List<List<Integer>> groupSizesRef) {
		if (n == 1) {
			List<Integer> groupSizes = new LinkedList<>();
			List<Character> ret = new LinkedList<>();

			groupSizes.add(1);
			groupSizesRef.add(groupSizes);
			ret.add('1');
			return ret;
		}

		List<Character> str = say(n - 1, groupSizesRef);

		return sayCharacters(str, groupSizesRef);
	}

	private List<Character> sayCharacters(List<Character> str,
			List<List<Integer>> groupSizesRef) {
		LinkedList<Integer> newGroupSizes = new LinkedList<>();
		List<Integer> groupSizes = groupSizesRef.get(0);
		// the previous group representative in the new string:
		Character prevGroupRep = '\0';
		// the prefix of the new string that has been processed:
		LinkedList<Character> prefix = new LinkedList<>();

		for (Integer groupSize : groupSizes) {
			Character groupRep = str.get(0);
			LinkedList<Character> groupSizeChars = toChars(groupSize);

			for (Character gsChar : groupSizeChars) {
				if (gsChar == prevGroupRep)
					newGroupSizes.add(newGroupSizes.removeLast() + 1);
				else
					newGroupSizes.add(1);
				prevGroupRep = gsChar;
			}
			if (groupRep.equals(groupSizeChars.getLast()))
				newGroupSizes.add(newGroupSizes.removeLast() + 1);
			else
				newGroupSizes.add(1);
			prefix.addAll(groupSizeChars);
			prefix.add(groupRep);
			prevGroupRep = groupRep;
			str = str.subList(groupSize, str.size());
		}
		groupSizesRef.clear();
		groupSizesRef.add(newGroupSizes);
		return prefix;
	}

	private LinkedList<Character> toChars(int n) {
		LinkedList<Character> ret = new LinkedList<>();

		while (n > 0) {
			int digit = n - (n / 10) * 10;

			ret.addLast((char) (digit + '0'));
			n = n / 10;
		}
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().countAndSay(25));
	}
}
