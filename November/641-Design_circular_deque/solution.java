class MyCircularDeque {
    int arr[];
    int b,e; // begin & end;
    int size;
    
    public MyCircularDeque(int k) {
	arr = new int[k];
	b = e = 0;
	size = 0;
    }
    
    public boolean insertFront(int value) {
        if (size == arr.length)
	    return false;
	b = (b + arr.length - 1) % arr.length;
	arr[b] = value;
	size++;
	return true;
    }
    
    public boolean insertLast(int value) {
        if (size == arr.length)
	    return false;
	arr[e] = value;
	e = (e + 1) % arr.length;
	size++;
	return true;
    }
    
    public boolean deleteFront() {
        if (size == 0)
	    return false;
	b = (b + 1) % arr.length;
	size--;
	return true;
    }
    
    public boolean deleteLast() {
        if (size == 0)
	    return false;
	e = (e + arr.length - 1) % arr.length;
	size--;
	return true;
    }
    
    public int getFront() {
	return size == 0 ? -1 : arr[b];
    }
    
    public int getRear() {
	return size == 0 ? -1 : arr[(e + arr.length - 1) % arr.length];
    }
    
    public boolean isEmpty() {
	return size == 0;
    }
    
    public boolean isFull() {
        return size == arr.length;
    }
}
