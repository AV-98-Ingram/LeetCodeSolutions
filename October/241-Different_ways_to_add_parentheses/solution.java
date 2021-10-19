import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {

    int[] nums;
    char[] ops;
    Map<int[], List<Integer>> cache = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
	nums = new int[expression.length()];
	ops = new char[expression.length()];

	int nnums = parse(expression.toCharArray());

	List<Integer> l = new LinkedList<>();

	l.addAll(f(0, nnums));
	return l;
    }

    private List<Integer> f(int start, int end) {
	List<Integer> results = new LinkedList<>();

	if (start == end - 1) {
	    results.add(nums[start]);
	    return results;
	}

	int[] key = new int[] { start, end };
	List<Integer> cached = cache.get(key);

	if (cached != null)
	    return cached;
	for (int n = start + 1; n < end; n++) {
	    List<Integer> lefts = f(start, n);
	    List<Integer> rights = f(n, end);

	    for (int left : lefts)
		for (int right : rights)
		    results.add(compute(left, ops[n - 1], right));
	}
	cache.put(key, results);
	return results;
    }

    private int parse(char[] expr) {
	int i = 0;
	int np = 0, op = 0;
	int num = 0;

	while (i < expr.length && '0' <= expr[i] && expr[i] <= '9') {
	    num = num * 10 + expr[i] - '0';
	    i++;
	}
	nums[np++] = num;
	while (i < expr.length) {
	    ops[op++] = expr[i++];
	    num = 0;
	    while (i < expr.length && '0' <= expr[i] && expr[i] <= '9') {
		num = num * 10 + expr[i] - '0';
		i++;
	    }
	    nums[np++] = num;
	}
	return np;
    }

    private int compute(int a, char op, int b) {
	switch (op) {
	case '+':
	    return a + b;
	case '-':
	    return a - b;
	case '*':
	    return a * b;
	}
	assert false;
	return 0;
    }

    public static void main(String[] args) {
	new Solution().diffWaysToCompute("2-1-1");
    }
}
