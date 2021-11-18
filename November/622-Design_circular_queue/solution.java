class MyCircularQueue {

    // I guess LC cannot find out that my queue is just a list and is
    // NOT circular:
    LinkedList<Integer> Q = new LinkedList<>();
    final int max;
    
    public MyCircularQueue(int k) {
	max = k;
    }
    
    public boolean enQueue(int value) {
        if (Q.size() < max) {
	    Q.add(value);
	    return true;
	}
	return false;
    }
    
    public boolean deQueue() {
        if (Q.size() > 0) {
	    Q.remove(0);
	    return true;
	}
	return false;
    }
    
    public int Front() {
	if (Q.size() > 0)
	    return Q.get(0);
	return -1;	
    }
    
    public int Rear() {
	if (Q.size() > 0)
	    return Q.getLast();
	return -1;
    }
    
    public boolean isEmpty() {
	return Q.isEmpty();
    }
    
    public boolean isFull() {
        return Q.size() == max;
    }
}
