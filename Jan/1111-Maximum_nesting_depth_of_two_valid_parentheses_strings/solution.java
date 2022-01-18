import java.util.LinkedList;

class Solution {
    public int[] maxDepthAfterSplit(String seq) {
	final int len = seq.length();
	int[] result = new int[seq.length()];
	int start = 0;

	do {
	    start = f(seq, start, result);
	} while (start < len);
	return result;
    }

    int f(String seq, int start, int[] result) {
	LinkedList<Integer> stack = new LinkedList<>();
	int i = start;
	int depth = 0;

	stack.add(i++);
	while (!stack.isEmpty()) {
	    char c = seq.charAt(i);

	    depth = Math.max(depth, stack.size());
	    if (c == '(') {
		stack.add(i++);
	    } else {
		stack.removeLast();
		i++;
	    }
	}

	int end = i;

	if (depth == 1)
	    return end;

	int half = depth / 2;

	i = start;
	stack.add(i++);
	while (!stack.isEmpty()) {
	    char c = seq.charAt(i);

	    if (c == '(') {
		stack.add(i++);
	    } else {
		int size = stack.size();
		int openIdx = stack.removeLast();

		if (size <= half) {
		    result[openIdx] = 1;
		    result[i] = 1;
		}
		i++;
	    }
	}
	return end;
    }

    public static void main(String[] args) {
	new Solution().maxDepthAfterSplit("(())(())");
    }
}
