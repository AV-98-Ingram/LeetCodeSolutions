class MyHashMap {

    class MyList {
	List<int[]> l = new LinkedList<>();
    }

    MyList[] arr;
    int capacity = 1000;
    int size = 0;
    
    public MyHashMap() {
	arr = new MyList[capacity];
    }
    
    public void put(int key, int value) {
	int hashCode =  key % capacity;

	if (arr[hashCode] == null)
	    arr[hashCode] = new MyList();

	Iterator<int[]> iter = arr[hashCode].l.iterator();

	while (iter.hasNext()) {
	    int[] item = iter.next();

	    if (item[0] == key) {
		item[1] = value;
		return;
	    }
	}
	arr[hashCode].l.add(new int[] {key, value});
	size++;
    }
    
    public int get(int key) {
	int hashCode =  key % capacity;

	if (arr[hashCode] == null)
	    return -1;
	for (int[] item : arr[hashCode].l)
	    if (item[0] == key)
		return item[1];
	return -1;
    }
    
    public void remove(int key) {
	int hashCode =  key % capacity;
	
	if (arr[hashCode] == null)
	    return;
	
	Iterator<int[]> iter = arr[hashCode].l.iterator();
	List<int[]> items = arr[hashCode].l;
	
	while (iter.hasNext()) {
	    int[] item = iter.next();
	    
	    if (item[0] == key) {
		iter.remove();
		size--;
		return;
	    }
	}
    }
}

