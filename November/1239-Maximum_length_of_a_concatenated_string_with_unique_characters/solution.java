import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

class Solution {

    public int maxLength(List<String> arr) {
	BitSet[] theArr = new BitSet[arr.size()];
	int i = 0;

	for (String s : arr)
	    theArr[i++] = toBitSet(s);

	BitSet allChars = new BitSet(26);

	return maxLen(theArr, 0, allChars);
    }

    private BitSet toBitSet(String s) {
	BitSet bs = new BitSet(26);

	for (char c : s.toCharArray()) {
	    if (!bs.get(c - 'a'))
		bs.set(c - 'a');
	    else {
		bs.clear();
		return bs;
	    }
	}
	return bs;
    }

    private int maxLen(BitSet[] arr, int pos, BitSet allChars) {
	if (pos == arr.length)
	    return 0;

	BitSet tmp = new BitSet(26);

	tmp.or(allChars);
	tmp.and(arr[pos]);
	if (tmp.isEmpty()) {
	    // no conflict
	    int len1, len2;
	    len1 = maxLen(arr, pos + 1, allChars);
	    tmp.or(allChars);
	    tmp.or(arr[pos]);
	    len2 = maxLen(arr, pos + 1, tmp) + arr[pos].cardinality();
	    return Math.max(len2, len1);
	}
	// conflict, cannot include arr[pos]:
	return maxLen(arr, pos + 1, allChars);
    }

    public static void main(String[] args) {
	System.out.print(new Solution()
			 .maxLength(Arrays.asList(new String[] { "un", "iq", "ue" })));
    }
}
