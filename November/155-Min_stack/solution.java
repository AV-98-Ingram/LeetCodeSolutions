import java.util.LinkedList;

class MinStack {

	LinkedList<Integer> stack = new LinkedList<>();

	public MinStack() {

	}

	public void push(int val) {
		if (stack.isEmpty()) {
			stack.addFirst(val); // push val
			stack.addFirst(val); // init min
		} else {
			int min = stack.peek();

			min = val < min ? val : min;
			stack.addFirst(val);
			stack.addFirst(min);
		}
	}

	public void pop() {
		stack.removeFirst();
		stack.removeFirst();
	}

	public int top() {
		return stack.get(1); // peek the second
	}

	public int getMin() {
		return stack.peek();
	}
}
