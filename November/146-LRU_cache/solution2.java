class LRUCache {

    // simply write it again in Feb 3rd.  This code is lengthier than my old code as I don't reuse "get" in "put"
    class Node {
	Node prev = null, next = null;
	int key;
	int val;
	
	Node (int key, int val) {
	    this.key = key;
	    this.val = val;
	}
    }
    
    private Map<Integer, Node> table = new HashMap<>();
    private Node head, tail;
    private int cap;
    
    public LRUCache(int capacity) {
	head = tail = null;
	cap = capacity;
    }
    
    public int get(int key) {
	Node node = table.get(key);
	
	if (node == null)
	    return -1;
	// move node to head
	moveToHead(node);
	return node.val;
    }
    
    public void put(int key, int value) {
	Node node = table.get(key);
	
	if (node != null) {
	    node.val = value;
	    // move node to head:
	    moveToHead(node);
	    return;
	} else if (table.size() == cap) {
	    // remove the tail node
	    table.remove(tail.key);
	    if (tail == head)
		tail = head = null;
	    else {
		tail = tail.prev;
		tail.next = null;
	    }
	} 
	node = new Node(key, value);	    
	table.put(key, node);
	// add node to head
	if (head == null) 
	    head = tail = node;
	else {
	    node.next = head;
	    head.prev = node;
	    head = node;
	}	    
    }

    private void moveToHead(Node node) {
	if (head == node)
	    return;
	
	Node prev = node.prev;
	Node next = node.next;
	
	node.prev = null;
	node.next = head;
	head.prev = node;
	assert prev != null;
	prev.next = next;
	if (next != null)
	    next.prev = prev;
	head = node;	
	if (tail == node)
	    tail = prev;
    }
}
