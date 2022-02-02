class MaxStack {

    LinkedList<Integer> l = new LinkedList<>();
    
    public MaxStack() {
        
    }
    
    public void push(int x) {
	int max = l.isEmpty() ? x : Math.max(l.get(1), x);
	
	l.addFirst(max);
        l.addFirst(x);
    }
    
    public int pop() {
	int ret = l.removeFirst();
	l.removeFirst();
	return ret;
    }
    
    public int top() {
        return l.peek();
    }
    
    public int peekMax() {
        return l.get(1);
    }
    
    public int popMax() {
	LinkedList<Integer> tmp = new LinkedList<>();
	int curr = l.removeFirst();
	int max = l.removeFirst();
	
	while (curr != max) {
	    tmp.addFirst(curr);
	    curr = l.removeFirst();
	    l.removeFirst();
	}
	for (int n : tmp)
	    push(n);
	return max;
    }
}
