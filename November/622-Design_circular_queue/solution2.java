class MyCircularQueue {

    int arr[];
    int b,e; // begin and end
    int size;
    
    public MyCircularQueue(int k) {
        arr = new int[k];
	b = 0;
	e = 0;
	size = 0;
    }
    
    public boolean enQueue(int value) {
	if (size == arr.length)
	    return false;
	arr[e] = value;
	e = (e + 1) % arr.length;
	size++;
	return true;
    }
    
    public boolean deQueue() {
	if (size == 0)
	    return false;
	b = (b + 1) % arr.length;
	size--;
	return true;
    }
    
    public int Front() {
        return size == 0 ? -1 : arr[b];
    }
    
    public int Rear() {
        return size == 0 ? -1 : arr[(e + arr.length - 1) % arr.length];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == arr.length;
    }
}
