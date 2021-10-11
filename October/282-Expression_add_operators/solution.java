import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * This problem gets me sweaty!
 * </p>
 * 
 * Things that I missed!:
 * <ol>
 * <li>skipping numbers with leading zeroes</li>
 * <li>the operator precedence, e.g. 1 + 2 * 3</li>
 * <li>The input string length is maximum 10 which can go beyond the limit of
 * integer</li>
 * <li>All digits must be used. For example, "0" is NOT an answer of the string
 * "00" with target = 0.</li>
 * </ol>
 * The idea here, <code>
 * Let f(x) be a set of tuples {(a, b, str)}, where
 * str represents one possibility up until x (inclusive);
 * b is the last "adder"
 * a is the sum of all "adder"s before b.
 * 
 * For example, some f(3) = {..., (1, -2, "1-2*1"), (-1, 1, "1-2+1"),  (-1, -1, "1-2-1")}
 * </code> The reason we split the arithmetic result of the digits into two
 * adders is for dealing with operator precedence.
 * 
 * Thus we can compute f(i) for i from 0 to the last index n of the given
 * string. The computation of f(i+1) only depends on f(i).
 */
class Solution {

    class Triple {
	long firstAdden;
	long secondAdden;
	String str;
	int end;

	Triple(long first, long second, String str, int end) {
	    this.firstAdden = first;
	    this.secondAdden = second;
	    this.str = str;
	    this.end = end;
	}

	public String toString() {
	    return this.str;
	}
    }

    Map<Integer, List<Triple>> prefixes = new HashMap<>();
    Long[][] nums;

    public List<String> addOperators(String num, int target) {
	char[] s = num.toCharArray();
	List<String> results = new LinkedList<>();

	nums = new Long[s.length][s.length];
	for (int i = 0; i < s.length; i++)
	    f(s, i);
	for (Triple triple : prefixes.get(s.length - 1)) {
	    if (triple.end == s.length - 1)
		if (triple.firstAdden + triple.secondAdden == target)
		    results.add(triple.str);
	}
	return results;
    }

    private List<Triple> f(char[] s, int end) {
	List<Triple> results = new LinkedList<>();
	long num = getNum(s, 0, end);

	// if (end > 0 ==> s[0] != 0), this number is valid
	if (end == 0 || s[0] != '0')
	    results.add(new Triple(0, num, String.valueOf(num), end));
	for (int i = 0; i < end; i++) {
	    num = getNum(s, i + 1, end);
	    // skip leading zero unless this '0' is the end-th digit
	    if (i + 1 < end && s[i + 1] == '0')
		continue;

	    for (Triple triple : prefixes.get(i)) {
		results.add(new Triple(triple.firstAdden + triple.secondAdden,
				       num, triple.str + "+" + num, end));
		results.add(new Triple(triple.firstAdden + triple.secondAdden,
				       -num, triple.str + "-" + num, end));
		results.add(new Triple(triple.firstAdden,
				       triple.secondAdden * num, triple.str + "*" + num, end));
	    }
	}
	prefixes.put(end, results);
	return results;
    }

    private long getNum(char[] s, int start, int end) {
	if (nums[start][end] != null)
	    return nums[start][end];

	long num = end > start ? nums[start][end - 1] * 10 + (s[end] - '0')
	    : (s[end] - '0');

	nums[start][end] = num;
	return num;
    }

    public static void main(String[] args) {
	System.out.println(
			   new Solution().addOperators("2147483648", -2147483648));
    }
}
