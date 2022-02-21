import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
	List<Integer> results = new LinkedList<>();
	List<Integer> lowDigits = getDigits(low);
	int size = lowDigits.size();
	int firstD = lowDigits.get(0);
	int[] num = getSeqNum(firstD, size);

	while (num[0] <= high && size <= 9) {
	    if (num[0] >= low)
		results.add(num[0]);
	    if (num[1] < 9) {
		firstD++;
	    } else {
		firstD = 1;
		size++;
	    }
	    num = getSeqNum(firstD, size);
	}
	return results;
    }

    private List<Integer> getDigits(int n) {
	LinkedList<Integer> digits = new LinkedList<>();

	while (n > 0) {
	    int digit = n % 10;

	    digits.addFirst(digit);
	    n = n / 10;
	}
	return digits;
    }

    private int[] getSeqNum(int first, int size) {
	int num = 0;
	int i = 1;
	int last = first + size - 1;

	if (last > 9)
	    return new int[] { 0, 9 }; // return {0, 9}

	int ret[] = new int[2];

	ret[1] = last;
	while (last >= first) {
	    num = i * last + num;
	    i = i * 10;
	    last--;
	}
	ret[0] = num;
	return ret;
    }

    public static void main(String[] args) {
	new Solution().sequentialDigits(10, 1000000000);
    }
}
